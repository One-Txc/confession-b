package com.didispace.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by txc on 17-12-3.
 */
public class WebUtil {

    /**
     * 判断ajax请求
     *
     * @author : jackphang
     * @date :2016年4月15日 下午4:48:29
     */
    public static boolean isAjax(HttpServletRequest request) {
        boolean ajax = (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request
                .getHeader("X-Requested-With").toString()));
        if (!ajax) {
            Integer index = request.getRequestURI().lastIndexOf(".");
            if (index > 0) {
                String reqSuffix = request.getRequestURI().substring(index);
                if (reqSuffix.equalsIgnoreCase(".ajax")) {
                    return true;
                }
            }
        }
        return ajax;
    }
}