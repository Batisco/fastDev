package com.batisco.fastDev.controller;

import com.batisco.fastDev.model.exceptions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class.getName());

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseException> handle(RuntimeException e) {
        logger.error("Unexpected exception", e);
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(value = AbstractDomainException.class)
    public ResponseEntity<ResponseException> handle(AbstractDomainException e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(new ResponseException(HttpStatus.BAD_REQUEST, e.getMessage()));
    }


    public static class ResponseException {

        private LocalDateTime timestamp;
        private String httpStatus;
        private int error;
        private String message;

        public ResponseException(HttpStatus status, String message) {
            this.message = message;
            httpStatus = status.getReasonPhrase();
            error = status.value();
            timestamp = LocalDateTime.now();
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(String httpStatus) {
            this.httpStatus = httpStatus;
        }

        public int getError() {
            return error;
        }

        public void setError(int error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ResponseException that = (ResponseException) o;
            return error == that.error &&
                    Objects.equals(timestamp, that.timestamp) &&
                    Objects.equals(httpStatus, that.httpStatus) &&
                    Objects.equals(message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, httpStatus, error, message);
        }

        @Override
        public String toString() {
            return "ResponseException{" +
                    "timestamp=" + timestamp +
                    ", httpStatus='" + httpStatus + '\'' +
                    ", error=" + error +
                    ", message='" + message + '\'' +
                    '}';
        }

    }

}
