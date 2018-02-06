package com.jp.base.action;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import com.jp.base.dubbo.service.DubboService;
import com.jp.base.model.Json;
import com.jp.base.tool.utils.CONSTANT;

@Controller("com.jp.base.action.BaseApi")
public class BaseApi {

    private static final Logger logger = LoggerFactory.getLogger(BaseApi.class);

    @Resource(name = "dubboService")
    DubboService service;

    public DubboService getDubboService(String ver) {

        switch (ver) {
            case "1.0.0":
                return service;
            default:
                logger.info(CONSTANT.COMMON.ERROR_SERVICE_NOT_EXIST_MESSAGE);
        }
        return null;

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Json getInfo(String code, String msg, String v) {
        Json json = new Json();
        JSONObject object = new JSONObject();
        json.setC(code);
        json.setM(msg);
        json.setV(v);
        json.setP(object);
        return json;
    }

    @SuppressWarnings("rawtypes")
    protected Json getSuccessObj(String code, String msg, String v) {
        return getInfo(code, msg, v);
    }

}
