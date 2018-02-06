package com.jp.base.model;


/**
 * @param <T>
 * @param <T>
 * @ClassName: 返回基类
 * @Description: TODO
 * @author: leven
 * @date: 2016年8月3日 下午3:31:53
 */
public class Json<T> implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String c = "";

    private String m = "";

    private String v = "";

    private T p;

    public T getP() {
        return p;
    }

    public void setP(T p) {
        this.p = p;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Json() {
        super();
    }

}
