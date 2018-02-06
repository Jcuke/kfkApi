package com.jp.base.dubbo.bean;

import java.io.Serializable;

public class DubboOutArgsBean implements Serializable {

    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description: TODO
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     */
    private String txid;

    /**
     * 响应时间
     */
    private String respTime;

    /**
     * 响应流水号
     */
    private String respSeq;

    /**
     * 返回参数
     */
    private String resParam;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    public String getResParam() {
        return resParam;
    }

    public void setResParam(String resParam) {
        this.resParam = resParam;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getRespTime() {
        return respTime;
    }

    public void setRespTime(String respTime) {
        this.respTime = respTime;
    }

    public String getRespSeq() {
        return respSeq;
    }

    public void setRespSeq(String respSeq) {
        this.respSeq = respSeq;
    }


    @Override
    public String toString() {
        return "DubboOutArgsBean [txid=" + txid + ", respTime=" + respTime + ", respSeq=" + respSeq + ", resParam=" + resParam + "]";
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

}
