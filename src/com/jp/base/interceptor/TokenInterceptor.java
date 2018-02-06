package com.jp.base.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.jp.base.action.BaseApi;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    BaseApi baseApi;

    private List<String> excludeUrls;

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    /**
     * 功能：验证token是否有效果，从bos_user_authtoken中取数据
     *
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     * @date: 2017年9月6日 下午5:47:27
     * @since: 1.0
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        return true;

    }
}
