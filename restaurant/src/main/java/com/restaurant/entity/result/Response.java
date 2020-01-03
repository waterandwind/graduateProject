package com.restaurant.entity.result;


import java.io.Serializable;

public class Response<T> implements Serializable {
    private int code;

    private String msg;

    private T data;

    //设置get方法，否则无法转换为json
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }


    private Response() {
    }

    private Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> Response success() {
        return builder().code(ResponseStatus.SUCCESS).build();
    }

    public static <T> Response success(String msg) {
        return builder().code(ResponseStatus.SUCCESS).msg(msg).build();
    }

    public static <T> Response success(String msg, T data) {
        return builder().code(ResponseStatus.SUCCESS).msg(msg).data(data).build();
    }

    public static <T> Response bizError(String msg) {
        return builder().code(ResponseStatus.BIZ_ERROR).msg(msg).build();
    }
    public static <T> Response accountOrPasswordError(String msg) {
        return builder().code(ResponseStatus.ERROR).msg(msg).build();
    }
    public static <T> Response notFound() {
        return builder().code(ResponseStatus.NOT_FOUND).build();
    }

    public static <T> Response notFound(String msg) {
        return builder().code(ResponseStatus.NOT_FOUND).msg(msg).build();
    }

    public static Response noPermission(String msg) {
        return builder().code(ResponseStatus.INVALID_TOKEN).build();
    }

    public static Response versionError(String msg) {
        return builder().code(ResponseStatus.ERROR).msg(msg).build();
    }

    public static <T> ResponseBuilder builder() {
        return new ResponseBuilder<T>();
    }

    public static class ResponseBuilder<T> {
        private int code;

        private String msg;

        private T data;

        public Response build() {
            Response response = new Response<>(this.code, this.msg, this.data);
            return response;
        }

        public ResponseBuilder code(ResponseStatus status) {
            this.code = status.code;
            return this;
        }

        public ResponseBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ResponseBuilder data(T data) {
            this.data = data;
            return this;
        }
    }
}
