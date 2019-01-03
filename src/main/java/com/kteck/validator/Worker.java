package com.kteck.validator;

import com.kteck.model.ProxyIpNetRef;
import com.kteck.model.ProxyIp;
import com.kteck.model.ProxyNet;
import com.kteck.model.ValidateResult;
import com.kteck.utils.WebTools;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class Worker implements Callable<ValidateResult> {

    private final ProxyIpNetRef proxyIpNetRef;
    private final ProxyIp proxyIp;
    private final ProxyNet proxyNet;

    public Worker(ProxyIpNetRef proxyIpNetRef, ProxyIp proxyIp, ProxyNet proxyNet) {
        this.proxyIpNetRef = proxyIpNetRef;
        this.proxyIp = proxyIp;
        this.proxyNet = proxyNet;
    }


    @Override
    public ValidateResult call() {
        ValidateResult validateResult = new ValidateResult();
        validateResult.setIpId(proxyIp.getId());
        validateResult.setNetId(proxyNet.getId());
        validateResult.setRefId(proxyIpNetRef.getId());

        Map<String, String> proxyMap = new HashMap(3);
        proxyMap.put("ip", proxyIp.getIp());
        proxyMap.put("port", proxyIp.getPort());
        try {
            long start = System.currentTimeMillis();
            Map<String, Object> resultMap = WebTools.get(proxyNet.getUrl(), proxyMap);
            validateResult.setResultCode(Integer.parseInt(String.valueOf(
                    resultMap.getOrDefault(WebTools.KEY_STATUS_CODE, "999"))));
            validateResult.setResultContent(
                    String.valueOf(resultMap.getOrDefault(WebTools.KEY_CONTENT, "")));
            validateResult.setSpeed(System.currentTimeMillis() - start);
        } catch (IOException e) {
            e.printStackTrace();
            validateResult.setResultCode(999);
            validateResult.setResultContent(e.getMessage());
        }

        return validateResult;
    }
}
