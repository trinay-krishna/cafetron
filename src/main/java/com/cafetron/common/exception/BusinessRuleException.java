package com.cafetron.common.exception;

/**
 * Thrown when a business rule is violated, e.g. insufficient wallet balance,
 * out-of-stock item, order locked, ordering window closed, past cutoff time.
 */
public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
