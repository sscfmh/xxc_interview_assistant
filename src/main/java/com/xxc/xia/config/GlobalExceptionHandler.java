package com.xxc.xia.config;

import com.xxc.xia.common.exception.BizException;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * desc..
 *
 * @author xxc
 * @version 2024/9/15 2:01
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        String allError = result.getFieldErrors().stream()
            .map(error -> error.getField() + " " + error.getDefaultMessage())
            .collect(Collectors.joining(" | "));

        return new ResponseEntity<>(ResultFactory.error(allError), HttpStatus.OK);
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<Result<Object>> handleValidationExceptions(BizException ex) {
        log.error("业务异常", ex.getMessage(), ex);
        return new ResponseEntity<>(ResultFactory.error(ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Result<Object>> handleValidationExceptions(Throwable ex) {
        log.error("系统异常", ex.getMessage(), ex);
        return new ResponseEntity<>(ResultFactory.error(ex.getMessage()), HttpStatus.OK);
    }
}
