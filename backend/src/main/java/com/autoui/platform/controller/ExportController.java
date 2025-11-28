package com.autoui.platform.controller;

import com.autoui.platform.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/export")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @PostMapping("/{framework}")
    public Map<String, Object> exportToFramework(
            @PathVariable String framework,
            @RequestBody Map<String, String> request) {
        
        String uiDefinition = request.get("uiDefinition");
        
        Map<String, String> exportedCode = exportService.exportToFramework(framework, uiDefinition);
        
        Map<String, Object> response = new HashMap<>();
        response.put("framework", framework);
        response.put("code", exportedCode);
        response.put("timestamp", System.currentTimeMillis());
        
        return response;
    }
}