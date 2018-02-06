package com.jp.base.tool.utils;

public class CONSTANT {

    /**
     * @ClassName: REDIS
     * @Description: REDIS常量
     * @date: 2017年08月12日 下午3:12:53
     */
    public static class REDIS {
        public final static String TOKEN_APP = "TOKEN:APP:";

        public final static Integer TOKEN_APP_ACTIVETIME = 60 * 120;
    }

    /**
     * @ClassName: COMMONMSGCODE
     * @Description: 通用信息码定义
     * @date: 2017年08月12日 下午3:12:53
     */
    public static class COMMON {

        public static final String ERROR_ACCESS_ILLEGAL = "-1";

        public static final String ERROR_ACCESS_ILLEGAL_MESSAGE = "非法访问";

        public static final String ERROR_LOGIN_TOEKN = "300";

        public static final String ERROR_LOGIN_TOEKN_MESSAGE = "登录信息已过期，请重新登录";

        public static final String ERROR_INTERFACE_CALL = "500";

        public static final String ERROR_INTERFACE_CALL_MESSAGE = "接口调用失败";

        public static final String ERROR_PARAM_NODE_HEAD_EMPTY = "501";

        public static final String ERROR_PARAM_NODE_HEAD_EMPTY_MESSAGE = "参数错误： head 节点不能为空";

        public static final String ERROR_PARAM_CHANNEL_EMPTY = "504";

        public static final String ERROR_PARAM_CHANNEL_EMPTY_MESSAGE = "参数错误：请求渠道来源  appcode 不能为空";

        public static final String ERROR_PARAM_OS_EMPTY = "507";

        public static final String ERROR_PARAM_OS_EMPTY_MESSAGE = "参数错误：操作系统信息  os 不能为空";

        public static final String ERROR_PARAM_OSVERSION_EMPTY = "508";

        public static final String ERROR_PARAM_OSVERSION_EMPTY_MESSAGE = "参数错误：操作系统版本  osversion 不能为空";

        public static final String ERROR_PARAM_BROWSER_EMPTY = "509";

        public static final String ERROR_PARAM_BROWSER_EMPTY_MESSAGE = "参数错误：浏览器名称  browser 不能为空";

        public static final String ERROR_PARAM_BROWSERVERSION_EMPTY = "510";

        public static final String ERROR_PARAM_BROWSERVERSION_EMPTY_MESSAGE = "参数错误：浏览器版本  browserversion 不能为空";

        public static final String ERROR_SERVICE_NOT_EXIST = "600";

        public static final String ERROR_SERVICE_NOT_EXIST_MESSAGE = "service版本不正确";

        public static final String ERROR_TOKEN_NOT_EXIST = "604";

        public static final String ERROR_TOKEN_NOT_EXIST_MESSAGE = "错误：请求token 不存在";


    }
}
