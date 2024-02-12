package com.lrpcn.quickdev.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.lrpcn.quickdev.common.ErrorCodeEnum;
import com.lrpcn.quickdev.common.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResultResponse<Void> CustomExceptionHandler(CustomException e) {
        log.error("CustomException", e);
        return ResultResponse.error(e);
    }

    @ExceptionHandler(value = NotLoginException.class)
    public ResultResponse<Void> handlerNotLoginException() {
        return ResultResponse.error(ErrorCodeEnum.NO_AUTH_ERROR);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        StringBuffer errorInfos = new StringBuffer();
        errors.forEach(error -> errorInfos.append(error.getDefaultMessage()).append(","));
        return ResultResponse.error(ErrorCodeEnum.PARAMETER_ERROR, errorInfos.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultResponse<Void> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultResponse.error(ErrorCodeEnum.SYSTEM_ERROR);
    }

}
