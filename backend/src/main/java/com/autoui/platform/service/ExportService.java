package com.autoui.platform.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExportService {

    public Map<String, String> exportToFramework(String framework, String uiDefinition) {
        Map<String, String> result = new HashMap<>();
        
        switch (framework.toLowerCase()) {
            case "vue":
                result = exportToVue(uiDefinition);
                break;
            case "react":
                result = exportToReact(uiDefinition);
                break;
            case "angular":
                result = exportToAngular(uiDefinition);
                break;
            case "flutter":
                result = exportToFlutter(uiDefinition);
                break;
            default:
                result.put("error", "Unsupported framework: " + framework);
        }
        
        return result;
    }
    
    private Map<String, String> exportToVue(String uiDefinition) {
        Map<String, String> result = new HashMap<>();
        result.put("html", "<template>\n  " + uiDefinition + "\n</template>");
        result.put("script", "<script>\nexport default {\n  name: 'GeneratedComponent'\n}\n</script>");
        result.put("style", "<style scoped>\n/* Generated styles */\n</style>");
        return result;
    }
    
    private Map<String, String> exportToReact(String uiDefinition) {
        Map<String, String> result = new HashMap<>();
        result.put("jsx", "import React from 'react';\n\nconst GeneratedComponent = () => {\n  return (\n    " + uiDefinition + "\n  );\n};\n\nexport default GeneratedComponent;");
        return result;
    }
    
    private Map<String, String> exportToAngular(String uiDefinition) {
        Map<String, String> result = new HashMap<>();
        result.put("html", uiDefinition);
        result.put("ts", "import { Component } from '@angular/core';\n\n@Component({\n  selector: 'app-generated',\n  templateUrl: './generated.component.html'\n})\nexport class GeneratedComponent {\n  \n}");
        result.put("css", "/* Generated styles */");
        return result;
    }
    
    private Map<String, String> exportToFlutter(String uiDefinition) {
        Map<String, String> result = new HashMap<>();
        result.put("dart", "import 'package:flutter/material.dart';\n\nclass GeneratedWidget extends StatelessWidget {\n  @override\n  Widget build(BuildContext context) {\n    return " + uiDefinition + ";\n  }\n}");
        return result;
    }
}