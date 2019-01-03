package com.kteck.model;

public class ValidateResult {
    private int refId;
    private int netId;
    private int ipId;
    private long speed;
    private int resultCode;
    private String resultContent;

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }

    public int getIpId() {
        return ipId;
    }

    public void setIpId(int ipId) {
        this.ipId = ipId;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    @Override
    public String toString() {
        return "ValidateResult{" +
                "refId=" + refId +
                ", netId=" + netId +
                ", ipId=" + ipId +
                ", speed=" + speed +
                ", resultCode=" + resultCode +
                ", resultContent='" + resultContent + '\'' +
                '}';
    }
}
