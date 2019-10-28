package com.order.webservice.config;


import com.order.webservice.common.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * created by zhangdingping at 2019/10/28
 * 全局异常处理
 */
@ControllerAdvice(
        basePackages = {"com.order.webservice.controller.*"},
        annotations = Controller.class)
@ResponseBody
public class GlobalExceptionHandler {

    private final BasicErrorController basicErrorController;

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    public GlobalExceptionHandler(BasicErrorController basicErrorController) {
        this.basicErrorController = basicErrorController;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, UnsupportedOperationException.class})
    public HttpResult handleBadRequest(HttpServletRequest req, Exception e) {
        logger.warn("Bad Request : ", e);
        return this.buildResponse(req, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public HttpResult common(HttpServletRequest req, Exception e) {
        HttpResult httpResult = this.buildResponse(req, HttpStatus.INTERNAL_SERVER_ERROR);
        logger.error(httpResult.getMessage(), e);
        return httpResult;
    }

    private HttpResult buildResponse(HttpServletRequest req, HttpStatus httpStatus) {
        ResponseEntity<Map<String, Object>> response = basicErrorController.error(req);
        //覆盖默认的500 (Internal Server Error)
        Objects.requireNonNull(response.getBody()).put("code", httpStatus);
        return new HttpResult(httpStatus.value(), response.getBody().get("message").toString(), null);
    }
}
