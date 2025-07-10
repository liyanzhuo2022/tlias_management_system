package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("There's something wrong:", e);
        return Result.error("There's something wrong, please contact the manager.");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("There's something wrong:", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry '");
        String errorMsg = message.substring(i);
        String[] arr = errorMsg.split(" ");
        return Result.error(arr[2] + " already exists.");
    }

    @ExceptionHandler
    public Result handleBusinessException(BusinessException e){
        log.error("There's something wrong:", e);
        return Result.error(e.getMessage());
    }
}
