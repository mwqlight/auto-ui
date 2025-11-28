package com.autoui.platform.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UIController {

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Auto UI Platform is running");
        return response;
    }

    @PostMapping("/generate-ui")
    public Map<String, Object> generateUI(@RequestBody Map<String, String> request) {
        // 这里将集成AI大模型来生成UI
        String description = request.get("description");
        
        Map<String, Object> response = new HashMap<>();
        response.put("description", description);
        response.put("generatedCode", "<!-- Generated UI code based on: " + description + " -->\n<div>\n  <h1>Generated Component</h1>\n  <p>This UI was generated from your description</p>\n</div>");
        response.put("framework", "vue");
        return response;
    }
}