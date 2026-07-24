package com.digitalheroes.pagepulse.controller;

import com.digitalheroes.pagepulse.dto.AuditRequest;
import com.digitalheroes.pagepulse.dto.AuditResponse;
import com.digitalheroes.pagepulse.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://website-audit-tool-eight-ebon.vercel.app/")
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/audit")
    public AuditResponse audit(@RequestBody AuditRequest request) {
        return auditService.analyze(request.getUrl());
    }

}
