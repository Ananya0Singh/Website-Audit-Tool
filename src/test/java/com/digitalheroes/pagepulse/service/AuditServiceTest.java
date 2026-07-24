package com.digitalheroes.pagepulse.service;

import com.digitalheroes.pagepulse.dto.AuditResponse;
import com.digitalheroes.pagepulse.exception.AuditException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class AuditServiceTest {

    private AuditService auditService;

    @BeforeEach
    void setUp() {
        auditService = new AuditService();

        // Simulate Spring injecting the timeout value
        ReflectionTestUtils.setField(auditService, "timeoutMillis", 8000);
    }
//happy path
    @Test
    void shouldAnalyzeValidWebsite() {
        AuditResponse response = auditService.analyze("https://example.com");

        assertEquals(200, response.getStatus());
        assertNotNull(response.getTitle());
        assertFalse(response.getTitle().isBlank());

        assertTrue(response.getResponseTime() >= 0);
        assertTrue(response.getWordCount() > 0);

        assertTrue(response.getH1Count() >= 1);
        assertTrue(response.getImagesWithoutAlt() >= 0);
    }
//faliure 1
    @Test
    void shouldThrowExceptionForEmptyUrl() {

        AuditException exception = assertThrows(
                AuditException.class,
                () -> auditService.analyze("")
        );

        assertEquals("URL cannot be empty", exception.getMessage());
    }
    //faliure 2
    @Test
    void shouldThrowExceptionForInvalidUrl() {

        AuditException exception = assertThrows(
                AuditException.class,
                () -> auditService.analyze("https://exa mple.com")
        );

        assertEquals("Invalid URL", exception.getMessage());
    }
    //faliure 3
    @Test
    void shouldThrowExceptionForUnreachableWebsite() {

        AuditException exception = assertThrows(
                AuditException.class,
                () -> auditService.analyze("https://this-domain-should-not-exist-123456789.com")
        );

        assertEquals("Website unreachable", exception.getMessage());
    }
}