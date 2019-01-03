package com.kteck.crawl;

import com.kteck.model.ProxyIp;
import com.kteck.model.Proxys;
import com.kteck.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;

@Service
public class Crawl {

    private static final ThreadPoolExecutor executor =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final Long TIME_INTERVAL = 15L * 60L * 1000L;

    @Autowired
    private ProxyService proxyService;

    @PostConstruct
    public void crawl() {
        new Thread(() -> {
            while (true) {
                if (proxyService.validateNeedCrawl() > 0) {
                    List<Proxys> proxys = proxyService.selectAllProxys();
                    for (Proxys proxy : proxys) {
                        Future<List<ProxyIp>> proxyFutureList = executor.submit(new Worker(proxy));
                        try {
                            List<ProxyIp> proxyIps = proxyFutureList.get();
                            for (ProxyIp proxyIp : proxyIps) {
                                proxyIp.setProtocol("http");
                                proxyIp.setScore((byte)20);
                                proxyService.insertProxyIp(proxyIp);
                            }
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
