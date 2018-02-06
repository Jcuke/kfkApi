package com.jp.base.tool.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;


public class RequestUtil {


    /**
     * 功能：获取用户真实IP地址
     *
     * @param request
     * @return
     * @date: 2017年9月6日 下午5:46:47
     * @since: 1.0
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 功能：拼装返回json报文
     *
     * @param code
     * @param msg
     * @param response
     * @param v
     * @date: 2017年9月6日 下午5:46:18
     * @since: 1.0
     */
    public static void writerMsg(String code, String msg, HttpServletResponse response, String v) {
        try {

            StringBuffer sb = new StringBuffer();
            sb.append("{\"c\":\"");
            sb.append(code);
            sb.append("\",\"m\":\"");
            sb.append(msg);
            sb.append("\",\"v\":\"");
            sb.append(v);
            sb.append("\"");
            sb.append(",\"p\":{}}");

            response.getWriter().print(sb.toString());
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 打印URL与参数
     * @return
     */
    public static void printUrlAndParam(String paramJson, String url) {
        // 获取URL
        String paramJsonPretty = "";

        try {

            Object json = JsonUtil.readValue(paramJson, Object.class);
            paramJsonPretty = JsonUtil.writerWithDefaultPrettyPrinter(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static String addIp(String p, String ip) {
        Map<String, Object> map = JsonUtil.readValue(p, HashMap.class);// readValue到一个原始数据类型.
        Map<String, String> hm = (Map<String, String>) map.get("h");
        hm.put("ip", ip);
        return JsonUtil.writeValueAsString(map);

    }

}
