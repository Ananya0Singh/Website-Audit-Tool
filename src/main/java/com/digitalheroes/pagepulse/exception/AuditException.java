package com.digitalheroes.pagepulse.exception;

/**
 * Thrown when a page audit cannot be completed
 * (invalid URL, unreachable site, timeout, non-HTML content, etc.)
 */
public class AuditException extends RuntimeException {

    public AuditException(String message) {
        super(message);
    }

}
