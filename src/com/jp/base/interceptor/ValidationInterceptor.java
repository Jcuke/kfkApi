package com.jp.base.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.jp.base.tool.utils.CONSTANT;
import com.jp.base.tool.utils.JsonUtil;
import com.jp.base.tool.utils.RequestUtil;


/**
 * spring mvc 拦截器具体实现类，主要用于报文必传参数基本校验，如非空、参数长度、超时等校验，对应spring-mvc.xml配置文件
 *
 * @date: 2017年9月7日 上午9:27:18
 */
public class ValidationInterceptor implements HandlerInterceptor {

    private List<String> excludeUrls;

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    private static final Logger logger = Logger.getLogger(ValidationInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
                                Exception exception) throws Exception {
        logger.debug("afterCompletion");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
                           ModelAndView modelAndView) throws Exception {
        logger.debug("postHandle");
    }

    /**
     * 功能：报文必传参数校验
     *
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     * @date: 2017年9月7日 上午9:26:22
     * @since: 1.0
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        if (url.indexOf("txinfo") > 0) {
            return true;
        }

        String p = request.getParameter("p");
        String v = request.getParameter("v");
        String r = request.getParameter("r");
        String param = r;
        if (r.indexOf("?") > 1) {
            r = r.substring(0, r.indexOf("?"));
            param = param.substring(param.indexOf("?") + 1, param.length()).replaceAll("param=", "");
            p = "{\"h\": {\"appcode\": null,\"appversion\": null,\"ostype\": null,\"osversion\": null,\"phonebrand\": null,\"phonemodel\": null,\"imei\": null,\"browser\": null,\"browserversion\": null,\"extinfo\": null,"
                    + "\"token\": \"\"},\"p\":" + param + "}";
        }
        request.setAttribute("p", p);
        request.setAttribute("v", v);
        request.setAttribute("r", r);
        if (this.excludeUrls.contains(r)) { // 放行的URL
            return true;
        }

        String requestURL = request.getRequestURL().toString() + "?v=" + v + "&r=" + r + "&p=";
        RequestUtil.printUrlAndParam(p, requestURL);

        // 获取根节点
        JsonNode node = JsonUtil.readTree(p);
        // 判断请求参数里有无 HEAD 节点
        if (node.get("h") == null || StringUtils.isEmpty(node.get("h").toString())) {
            RequestUtil.writerMsg(CONSTANT.COMMON.ERROR_PARAM_NODE_HEAD_EMPTY,
                    CONSTANT.COMMON.ERROR_PARAM_NODE_HEAD_EMPTY_MESSAGE, response, v);
            return false;
        }
        // 获取HEAD节点
        JsonNode sonNode = node.path("h");

        // 不是登录接口只要token验证
        if (!(r != null && r.equals("com.jp.unp.subject.unpei.organ.LoginService-login"))) {

            // token
            if (sonNode.get("token") == null) {
                RequestUtil.writerMsg(CONSTANT.COMMON.ERROR_TOKEN_NOT_EXIST,
                        CONSTANT.COMMON.ERROR_TOKEN_NOT_EXIST_MESSAGE, response, v);
                return false;
            }
        } else {

            // token
            if (sonNode.get("token") == null) {
                RequestUtil.writerMsg(CONSTANT.COMMON.ERROR_TOKEN_NOT_EXIST,
                        CONSTANT.COMMON.ERROR_TOKEN_NOT_EXIST_MESSAGE, response, v);
                return false;
            }
            // 请求渠道来源
            if (sonNode.get("appcode") == null || StringUtils.isEmpty(sonNode.get("appcode").asText())) {
                RequestUtil.writerMsg(CONSTANT.COMMON.ERROR_PARAM_CHANNEL_EMPTY,
                        CONSTANT.COMMON.ERROR_PARAM_CHANNEL_EMPTY_MESSAGE, response, v);
                return false;
            }
            // 系统信息
            if (sonNode.get("ostype") == null) {
                RequestUtil.writerMsg(CONSTANT.COMMON.ERROR_PARAM_OS_EMPTY,
                        CONSTANT.COMMON.ERROR_PARAM_OS_EMPTY_MESSAGE, response, v);
                return false;
            }
            // 系统版本
            if (sonNode.get("osversion") == null) {
                RequestUtil.writerMsg(CONSTANT.COMMON.ERROR_PARAM_OSVERSION_EMPTY,
                        CONSTANT.COMMON.ERROR_PARAM_OSVERSION_EMPTY_MESSAGE, response, v);
                return false;
            }
            // 浏览器名称
            if (sonNode.get("browser") == null) {
                RequestUtil.writerMsg(CONSTANT.COMMON.ERROR_PARAM_BROWSER_EMPTY,
                        CONSTANT.COMMON.ERROR_PARAM_BROWSER_EMPTY_MESSAGE, response, v);
                return false;
            }
            // 浏览器版本号
            if (sonNode.get("browserversion") == null) {
                RequestUtil.writerMsg(CONSTANT.COMMON.ERROR_PARAM_BROWSERVERSION_EMPTY,
                        CONSTANT.COMMON.ERROR_PARAM_BROWSERVERSION_EMPTY_MESSAGE, response, v);
                return false;
            }

        }

        return true;
    }

}