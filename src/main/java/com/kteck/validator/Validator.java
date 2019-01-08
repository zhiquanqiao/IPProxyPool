package com.kteck.validator;

import com.kteck.model.ProxyIp;
import com.kteck.model.ProxyIpNetRef;
import com.kteck.model.ProxyNet;
import com.kteck.model.ValidateResult;
import com.kteck.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class Validator {

    @Autowired
    private ProxyService proxyService;
    private static final ThreadPoolExecutor executor =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final Long TIME_INTERVAL = 60L * 1000L;

    @PostConstruct
    public void validate() {
        new Thread(() -> {
            while (true) {
                List<ProxyIpNetRef> proxyIpNetRefList = proxyService.selectAllProxyIpNetRef();
                if (proxyIpNetRefList != null && proxyIpNetRefList.size() > 0) {
                    for (Iterator<ProxyIpNetRef> iterator = proxyIpNetRefList.iterator(); iterator.hasNext(); ) {
                        ProxyIpNetRef proxyIpNetRef = iterator.next();
                        ProxyIp proxyIp = proxyService.getProxyIpById(proxyIpNetRef.getIpId());
                        ProxyNet proxyNet = proxyService.getProxyNetById(proxyIpNetRef.getNetId());
                        if(proxyIp == null || proxyNet == null){
                            proxyService.deleteProxyIpRef(proxyIpNetRef.getId());
                            continue;
                        }
                        Future<ValidateResult> validateResultFuture = executor.submit(new Worker(proxyIpNetRef, proxyIp, proxyNet));
                        try {
                            ValidateResult validateResult = validateResultFuture.get();
                            int responseCode = validateResult.getResultCode();
                            if (responseCode == 999 || responseCode == 521) {
                                proxyIp.setScore((byte) (Integer.valueOf(proxyIp.getScore()) - 1));
                                proxyIpNetRef.setSpeed(new BigDecimal(validateResult.getSpeed()));
                                proxyService.updateProxyIp(proxyIp);
                                proxyIpNetRef.setScore((byte) (Integer.valueOf(proxyIpNetRef.getScore()) - 1));
                                proxyService.updateProxyIpNetRef(proxyIpNetRef);
                            }
                            System.out.println(validateResult.toString());
                            proxyService.deleteNotAvailableIp();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }

                }
                try {
                    Thread.sleep(TIME_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
