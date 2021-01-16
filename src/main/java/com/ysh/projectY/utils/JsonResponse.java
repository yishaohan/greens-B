package com.ysh.projectY.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JsonResponse {
    private final Integer status;
    private final String msg;
    private final Object data;
    //    @JsonIgnore
    private final String detail;

    private JsonResponse(Integer status, String msg, Object data, String detail) {
        this.status = status;
        this.msg = msg;
        this.detail = detail;
        this.data = data;
    }

    public static JsonResponse success(Integer status, String i18nMessageKey) {
        return new JsonResponse(status, i18nMessageKey, null, null);
    }

    public static JsonResponse success(Integer status, String i18nMessageKey, String detail) {
        return new JsonResponse(status, i18nMessageKey, detail, null);
    }

    public static JsonResponse success(Integer status, String i18nMessageKey, Object data) {
        return new JsonResponse(status, i18nMessageKey, data, null);
    }

    public static JsonResponse success(Integer status, String i18nMessageKey, Object data, String detail) {
        return new JsonResponse(status, i18nMessageKey, data, detail);
    }

    public static JsonResponse failure(Integer status, String i18nMessageKey) {
        return new JsonResponse(status, i18nMessageKey, null, null);
    }

    public static JsonResponse failure(Integer status, String i18nMessageKey, String detail) {
        return new JsonResponse(status, i18nMessageKey, detail, null);
    }

    public static JsonResponse failure(Integer status, String i18nMessageKey, Object data, String detail) {
        return new JsonResponse(status, i18nMessageKey, data, detail);
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", detail='" + detail + '\'' +
                '}';
    }
}
