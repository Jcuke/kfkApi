package com.jp.base.dubbo.bean;

import java.io.Serializable;

/**
 * @ClassName: CssDubboInParamBean
 * @Description: TODO
 * @author: leven
 * @date: 2016年8月3日 上午10:50:04
 */
public class DubboInArgsBean implements Serializable {
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
     * 请求时间
     */
    private String reqTime;
    /**
     * 请求流水号
     */
    private String reqSeq;


    /**
     * 请求参数
     */
    private String reqParam;

    public String getReqParam() {
        return reqParam;
    }

    public String getReqSeq() {
        return reqSeq;
    }


    public String getReqTime() {
        return reqTime;
    }


    public String getTxid() {
        return txid;
    }

    public void setReqParam(String reqParam) {
        this.reqParam = reqParam;
    }

    public void setReqSeq(String reqSeq) {
        this.reqSeq = reqSeq;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }


    public void setTxid(String txid) {
        this.txid = txid;
    }


}
