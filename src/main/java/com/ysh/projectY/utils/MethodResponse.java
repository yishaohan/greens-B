package com.ysh.projectY.utils;

public class MethodResponse {
    private final boolean success;
    private final String i18nMessageKey;
    private final Object data;
    private final String detail;

    private MethodResponse(boolean success, String i18nMessageKey, String detail, Object data) {
        this.success = success;
        this.i18nMessageKey = i18nMessageKey;
        this.detail = detail;
        this.data = data;
    }

    public static MethodResponse success(String i18nMessageKey) {
        return new MethodResponse(true, i18nMessageKey, null, null);
    }

    public static MethodResponse success(String i18nMessageKey, String detail) {
        return new MethodResponse(true, i18nMessageKey, detail, null);
    }

    public static MethodResponse success(String i18nMessageKey, String detail, Object data) {
        return new MethodResponse(true, i18nMessageKey, detail, data);
    }

    public static MethodResponse failure(String i18nMessageKey) {
        return new MethodResponse(false, i18nMessageKey, null, null);
    }

    public static MethodResponse failure(String i18nMessageKey, String detail) {
        return new MethodResponse(false, i18nMessageKey, detail, null);
    }

    public static MethodResponse failure(String i18nMessageKey, String detail, Object data) {
        return new MethodResponse(false, i18nMessageKey, detail, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getI18nMessageKey() {
        return i18nMessageKey;
    }

    public String getDetail() {
        return detail;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "MethodResponse{" +
                "success=" + success +
                ", i18nMessageKey='" + i18nMessageKey + '\'' +
                ", data=" + data +
                ", detail='" + detail + '\'' +
                '}';
    }
}
