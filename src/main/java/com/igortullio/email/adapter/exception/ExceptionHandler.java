package com.igortullio.email.adapter.exception;

import com.igortullio.email.core.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error.ErrorBuilder errorBuilder = createError(exception, status);
        return super.handleExceptionInternal(exception, errorBuilder.build(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error.Object> problemObjects = exception.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> {
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Error.Object.builder()
                            .name(name)
                            .userMessage(objectError.getDefaultMessage())
                            .build();
                })
                .collect(Collectors.toList());
        Error.ErrorBuilder errorBuilder = createError(exception, status, problemObjects);
        errorBuilder.detail("Validation failed");

        return super.handleExceptionInternal(exception, errorBuilder.build(), headers, status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private Error.ErrorBuilder createError(Exception exception, HttpStatus status, List<Error.Object> objects) {
        Error.ErrorBuilder errorBuilder = createError(exception, status);
        errorBuilder.objects(objects);
        return errorBuilder;
    }

    private Error.ErrorBuilder createError(Exception exception, HttpStatus status) {
        return Error.builder()
                .status(status.value())
                .detail(exception.getMessage())
                .timestamp(OffsetDateTime.now())
                .title(status.getReasonPhrase());
    }

}
