package com.kteck.validator;

import com.kteck.model.ProxyIp;
import com.kteck.model.ProxyIpNetRef;
import com.kteck.model.ProxyNet;
import com.kteck.model.ValidateResult;
import com.kteck.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

@Service
public class Validator {

    @Autowired
    private ProxyService proxyService;
    private static final ExecutorService threadPool =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final CompletionService<ValidateResult> completionService
            = new ExecutorCompletionService(threadPool);
    private static final Long TIME_INTERVAL = 60L * 1000L;

    @PostConstruct
    @Transactional
    public void validate() {
        Thread validateThread = new Thread(() -> {
            while (true) {
                List<ProxyIpNetRef> proxyIpNetRefList = proxyService.selectAllProxyIpNetRef();
                if (proxyIpNetRefList != null && proxyIpNetRefList.size() > 0) {
                    for (Iterator<ProxyIpNetRef> iterator = proxyIpNetRefList.iterator(); iterator.hasNext(); ) {
                        ProxyIpNetRef proxyIpNetRef = iterator.next();
                        ProxyIp proxyIp = proxyService.getProxyIpById(proxyIpNetRef.getIpId());
                        ProxyNet proxyNet = proxyService.getProxyNetById(proxyIpNetRef.getNetId());
                        if (proxyIp == null || proxyNet == null) {
                            proxyService.deleteProxyIpRef(proxyIpNetRef.getId());
                            continue;
                        }
                        completionService.submit(new Worker(proxyIpNetRef, proxyIp, proxyNet));
                    }
                }
                try {
                    Thread.sleep(TIME_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        validateThread.setName("thread validate ");
        validateThread.start();

        Thread validateResultThread = new Thread(() -> {

            while (true) {
                Future<ValidateResult> validateResultFuture = null;
                try {
                    validateResultFuture = completionService.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (validateResultFuture != null)
                    try {
                        ValidateResult validateResult = validateResultFuture.get();
                        int responseCode = validateResult.getResultCode();
                        if (responseCode == 999 || responseCode == 521) {

                            ProxyIp proxyIp = proxyService.getProxyIpById(validateResult.getIpId());
                            if (proxyIp != null) {
                                proxyIp.setScore((byte) (Integer.valueOf(proxyIp.getScore()) - 1));
                                ProxyIpNetRef proxyIpNetRef =
                                        proxyService.getProxyIpNetRefByIpAndNetsId(
                                                validateResult.getIpId(), validateResult.getNetId());
                                if (proxyIpNetRef != null) {
                                    proxyIpNetRef.setSpeed(new BigDecimal(validateResult.getSpeed()));
                                    proxyService.updateProxyIp(proxyIp);
                                    proxyIpNetRef.setScore((byte) (Integer.valueOf(proxyIpNetRef.getScore()) - 1));
                                    proxyService.updateProxyIpNetRef(proxyIpNetRef);
                                }
                            }
                        }
                        System.out.println(validateResult.toString());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                proxyService.deleteNotAvailableIp();
            }
        });
        validateResultThread.setName("thread validate result");
        validateResultThread.start();
    }


}

