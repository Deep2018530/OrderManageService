package com.order.webservice.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * created by zhangdingping at 2019/10/23
 * 控制器响应结果集
 */
@ApiModel(value = "响应结果集")
@Data
public class HttpResult<T> {

    private static final Integer SUCCESS = 200;

    private static final Integer FAIL = 500;


    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("消息")
    private String message;

    @ApiModelProperty("返回结果集")
    private T resultBody;

    public HttpResult(Integer code, String message, T resultBody) {
        this.code = code;
        this.message = message;
        this.resultBody = resultBody;
    }

    public static <T> HttpResult<T> success(T resultBody) {
        return new HttpResult(SUCCESS, "success", resultBody);
    }

    public static <T> HttpResult<T> fail(T resultBody) {
        return new HttpResult<>(FAIL, "error", resultBody);
    }

    public static <T> HttpResult<T> fail(T resultBody, String message) {
        return new HttpResult<>(FAIL, message == null ? "error" : message, resultBody);
    }

}
