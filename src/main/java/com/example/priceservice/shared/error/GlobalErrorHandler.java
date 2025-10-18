package com.example.priceservice.shared.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @ExceptionHandler(PriceNotFoundException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handlePriceNotFound(
            PriceNotFoundException ex, ServerWebExchange exchange) {

        log.warn("Price not found: {}", ex.getMessage());
        var error = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        );

        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
    }

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleInvalidInput(
            ServerWebInputException ex, ServerWebExchange exchange) {

        log.warn("Invalid request: {}", ex.getMessage());

        String parameter = extractParameterName(ex.getMessage());
        String message;

        // Personalizar según el tipo de parámetro que falló
        if ("applicationDate".equalsIgnoreCase(parameter)) {
            message = "Invalid value for parameter 'applicationDate'. " +
                    "Expected ISO format: yyyy-MM-dd'T'HH:mm:ss";
        } else if ("productId".equalsIgnoreCase(parameter) || "brandId".equalsIgnoreCase(parameter)) {
            message = "Invalid value for parameter '" + parameter + "'. Expected a numeric value.";
        } else if ("unknown".equalsIgnoreCase(parameter)) {
            message = "Invalid request parameter. Please verify input types and formats.";
        } else {
            message = "Invalid value for parameter '" + parameter + "'.";
        }

        var error = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message,
                exchange.getRequest().getPath().value()
        );

        return Mono.just(ResponseEntity.badRequest().body(error));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiErrorResponse>> handleGeneric(
            Exception ex, ServerWebExchange exchange) {

        log.error("Unexpected error: ", ex);
        var error = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                exchange.getRequest().getPath().value()
        );

        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
    }

    /**
     * Extrae el nombre del parámetro del mensaje de error de Spring.
     * Ejemplo: "Failed to bind parameter 'productId'; Type mismatch."
     */
    private String extractParameterName(String rawMessage) {
        if (rawMessage == null) return "unknown";
        try {
            int start = rawMessage.indexOf("parameter '") + 11;
            int end = rawMessage.indexOf("'", start);
            if (start > 10 && end > start) {
                return rawMessage.substring(start, end);
            }
        } catch (Exception ignored) { }
        return "unknown";
    }
}
