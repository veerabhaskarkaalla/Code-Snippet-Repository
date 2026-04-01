package com.snippets.controller;

import com.snippets.entity.Snippet;
import com.snippets.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {
    
    @Autowired
    private SnippetService snippetService;
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        try {
            List<Snippet> allSnippets = snippetService.getAllSnippets();
            
            // Null safe checks
            if (allSnippets == null) {
                allSnippets = new ArrayList<>();
            }
            
            // Statistics
            long totalSnippets = allSnippets.size();
            long javaCount = allSnippets.stream().filter(s -> "Java".equals(s.getLanguage())).count();
            long pythonCount = allSnippets.stream().filter(s -> "Python".equals(s.getLanguage())).count();
            long jsCount = allSnippets.stream().filter(s -> "JavaScript".equals(s.getLanguage())).count();
            
            // Recent snippets (last 5) - null safe
            List<Snippet> recentSnippets = allSnippets.stream()
                    .sorted((s1, s2) -> s2.getCreatedAt().compareTo(s1.getCreatedAt()))
                    .limit(5)
                    .collect(Collectors.toList());
            
            model.addAttribute("totalSnippets", totalSnippets);
            model.addAttribute("javaCount", javaCount);
            model.addAttribute("pythonCount", pythonCount);
            model.addAttribute("jsCount", jsCount);
            model.addAttribute("recentSnippets", recentSnippets);
            
            return "dashboard";
            
        } catch (Exception e) {
            e.printStackTrace();
            // Default values pettu
            model.addAttribute("totalSnippets", 0);
            model.addAttribute("javaCount", 0);
            model.addAttribute("pythonCount", 0);
            model.addAttribute("jsCount", 0);
            model.addAttribute("recentSnippets", new ArrayList<>());
            return "dashboard";
        }
    }
}