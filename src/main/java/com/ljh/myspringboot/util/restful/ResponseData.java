package com.ljh.myspringboot.util.restful;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/10/11 13:58
 */
@ApiModel
public class ResponseData {

    @ApiModelProperty("返回信息")
    private final String message;
    @ApiModelProperty("返回状态码")
    private final int code;
    @ApiModelProperty("返回内容")
    private final Map<String, Object> data = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok() {
        return new ResponseData(200, "Ok");
    }

    public static ResponseData notFound() {
        return new ResponseData(404, "Not Found");
    }

    public static ResponseData badRequest() {
        return new ResponseData(400, "Bad Request");
    }

    public static ResponseData forbidden() {
        return new ResponseData(403, "Forbidden");
    }

    public static ResponseData unauthorized() {
        return new ResponseData(401, "unauthorized");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }

    public static ResponseData customerError() {
        return new ResponseData(1001, "customer Error");
    }
}
