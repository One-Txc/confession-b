package com.didispace.util.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: txc
 * @date: 18-8-2 上午8:37
 */
public class ResultData implements Serializable {

    /**
     * 成功
     */
    public static final String SUCCESS = "0000";

    /**
     * 失败
     */
    public static final String FAIL = "8888";

    /**
     * 异常
     */
    public static final String ERROR = "9999";

    private static final long serialVersionUID = 1L;
    private String status;// 响应业务状态码
    private String msg;// 响应消息
    private Map<String, Object> data;// 响应数据[V1.0版本的数据返回]
    private Map<String, Object> resultMap = new HashMap<>();
    private static String defaultErrorMsg = "服务器繁忙";
    private Object resp;//V1.0+版本返回的数据

    private ResultData() {

    }

    private ResultData(String status, String msg, Map<String, Object> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.resultMap = new HashMap<>();
    }

    /**
     * 返回结果集
     *
     * @author : jackphang
     * @date :2016年4月15日 上午11:15:31
     */
    public static ResultData build(String stutus, String msg, Map<String, Object> data) {
        return new ResultData(stutus, msg, data);
    }

    public static ResultData build(String stutus, String msg) {
        return new ResultData(stutus, msg, null);
    }

    /**
     * 返回失败结果集[带失败信息]
     *
     * @author : jackphang
     * @date :2016年4月15日 上午11:15:31
     */
    public static ResultData fail(String status, String msg) {
        return new ResultData(status, msg, null).clearResultMap();
    }

    /**
     * 返回失败结果集
     *
     * @author : jackphang
     * @date :2016年4月15日 上午11:15:31
     */
    public static ResultData fail(String msg) {
        return new ResultData(ResultData.FAIL, msg, null).clearResultMap();
    }

    /**
     * 返回成功结果集[带返回数据]
     *
     * @author : jackphang
     * @date :2016年4月15日 上午11:15:31
     */
    public static ResultData success(Map<String, Object> data) {
        ResultData resultData = new ResultData(ResultData.SUCCESS, null, data);
        if (resultData.resultMap != null && resultData.resultMap.size() <= 0) {
            resultData.clearResultMap();
        }
        return resultData;
    }

    public static ResultData success(String msg) {
        ResultData resultData = new ResultData(ResultData.SUCCESS, msg, null);
        if (resultData.resultMap != null && resultData.resultMap.size() <= 0) {
            resultData.clearResultMap();
        }
        return resultData;
    }

    public static ResultData success(String msg, Map<String, Object> data) {
        ResultData resultData = new ResultData(ResultData.SUCCESS, msg, data);
        if (resultData.resultMap != null && resultData.resultMap.size() <= 0) {
            resultData.clearResultMap();
        }
        return resultData;
    }

    public static ResultData successWhitResp(Object resp) {
        ResultData resultData = new ResultData(ResultData.SUCCESS, null, null);
        resultData.setResp(resp);
        return resultData.clearResultMap();
    }

    /**
     * 成功返回结果集[不返回数据]
     *
     * @author : jackphang
     * @date :2016年4月15日 上午11:15:31
     */
    public static ResultData success() {
        ResultData resultData = new ResultData(ResultData.SUCCESS, null, null);
        if (resultData.resultMap != null && resultData.resultMap.size() <= 0) {
            resultData.clearResultMap();
        }
        return resultData;

    }

    /**
     * 异常
     *
     * @author : jackphang
     * @date :2016年4月18日 上午11:06:02
     */
    public static ResultData error(String msg) {
        return new ResultData(ResultData.ERROR, msg, null).clearResultMap();
    }

    public static ResultData error() {
        return new ResultData(ResultData.ERROR, defaultErrorMsg, null).clearResultMap();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        // data 有可能是传递而来的 map数据、故而多定义了一个resultMap来接收返回数据
        if (data == null) {
            data = resultMap;
        } else {
            data.putAll(resultMap);
        }
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * 往返回结果集中添加数据
     *
     * @author : jackphang
     * @date :2016年4月15日 下午2:20:07
     */
    public ResultData put(String key, Object value) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
        }
        resultMap.put(key, value);
        return this;
    }

    /**
     * @param value
     * @return
     * @desc:往data中添加返回数据,key为小写的类名
     * @note:只适用于单个对象，不适用于集合
     * @auth:jackphang
     * @date:2016年12月20日 下午2:18:44
     */
    public ResultData put(Object value) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
        }
        String className = value.getClass().getName();
        String key = className.substring(className.lastIndexOf(".") + 1);
        key = key.substring(0, 1).toLowerCase() + key.substring(1);
        resultMap.put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) getData().get(key);
    }

    public void putAll(Map<String, Object> map) {
        resultMap.putAll(map);
    }

    public boolean isSuccess() {
        return status.equals(ResultData.SUCCESS);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public Object getResp() {
        return resp;
    }

    public void setResp(Object resp) {
        this.resp = resp;
    }


    public ResultData clearResultMap() {
        this.resultMap = null;
        return this;
    }
}
