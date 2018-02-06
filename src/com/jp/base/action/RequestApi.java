package com.jp.base.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.jp.base.dubbo.bean.DubboInArgsBean;
import com.jp.base.dubbo.bean.DubboOutArgsBean;
import com.jp.base.dubbo.service.DubboService;
import com.jp.base.model.Json;
import com.jp.base.tool.utils.CONSTANT;
import com.jp.base.tool.utils.JsonUtil;
import com.jp.base.tool.utils.RequestUtil;

@Controller
public class RequestApi {

    private static final Logger logger = LoggerFactory.getLogger(RequestApi.class);

    @Resource
    private BaseApi baseApi;

    /**
     * 公共接口入口，反射调用（dubboService.doPost）
     *
     * @param request
     * @return
     * @date: 2017年9月9日 上午9:48:05
     * @since: 1.0.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping(value = "request", method = RequestMethod.POST)
    @ResponseBody
    public Json request(HttpServletRequest request) {
        Json<?> j = null;
        DubboOutArgsBean outArgsBean = null;
        String v = request.getParameter("v");
        String r = request.getParameter("r");
        String p = request.getParameter("p");
        try {
            if (StringUtils.isEmpty(p)) {
                p = getBody(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //校验版本是否存在
            DubboService service = baseApi.getDubboService(v);
            if (service == null) {
                return baseApi.getSuccessObj(CONSTANT.COMMON.ERROR_SERVICE_NOT_EXIST, CONSTANT.COMMON.ERROR_SERVICE_NOT_EXIST_MESSAGE, v);
            }

            String ip = request.getRemoteAddr();// 返回发出请求的IP地址
            p = RequestUtil.addIp(p, ip);


            DubboInArgsBean inArgsBean = new DubboInArgsBean();
            inArgsBean.setTxid(r);
            inArgsBean.setReqParam(p);// 入参

            String requestURL = request.getRequestURL().toString() + "?v=" + v + "&r=" + r + "&p=";

            RequestUtil.printUrlAndParam(p, requestURL);

            outArgsBean = service.doPost(inArgsBean);
            if (StringUtils.isNotEmpty(outArgsBean.getResParam())
                    && "未找到对应的service接口！".equals(outArgsBean.getResParam())) {
                return baseApi.getSuccessObj("400", "未找到对应的service接口！", v);
            }
            logger.info("请求返回参数:" + outArgsBean.getResParam());
            j = JsonUtil.readValue(outArgsBean.getResParam(), Json.class);
        } catch (Exception e) {
            e.printStackTrace();
            return baseApi.getSuccessObj("400", r + ":接口异常！", v);
        }
        return j;
    }

    String getBody(HttpServletRequest request) throws Exception {
        ServletInputStream in = request.getInputStream();
        byte[] buf = new byte[8 * 1024];
        StringBuffer sbuf = new StringBuffer();
        int result;
        do {
            result = in.readLine(buf, 0, buf.length); // does +=
            if (result != -1) {
                sbuf.append(new String(buf, 0, result, "UTF-8"));
            }
        } while (result == buf.length); // loop only if the buffer was filled

        if (sbuf.length() == 0) {
            return null; // nothing read, must be at the end of stream
        }
        in.close();
        return sbuf.toString();
    }
}
