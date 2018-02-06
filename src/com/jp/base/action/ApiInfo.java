package com.jp.base.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/txinfo")
@Scope("prototype")
public class ApiInfo {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ApiInfo.class);
    String elideFileds = "appversion,browser,browserversion,client,clientversion,extinfo,imei,os,osversion,phonebrand,phonemodel";

    String ver = "1.0.0";

    @Resource
    private BaseApi baseApi;

    static boolean contains(String[] excludes, String name) {
        for (int i = 0; i < excludes.length; i++) {
            if (excludes[i] == null || excludes[i].length() < 1)
                continue;
            if (name.equals(excludes[i]))
                return true;
        }
        return false;
    }


    public static void main(String[] args) {

    }
}
