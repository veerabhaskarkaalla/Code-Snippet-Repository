package com.snippets.controller;

import com.snippets.entity.Snippet;
import com.snippets.entity.User;
import com.snippets.service.SnippetService;
import com.snippets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/snippets")
public class SnippetController {
    
    @Autowired
    private SnippetService snippetService;
    
    @Autowired
    private UserService userService;
    
    // My Snippets - List all snippets
    @GetMapping("")
    public String listSnippets(Model model) {
        try {
            System.out.println("=== Loading snippets list ===");
            List<Snippet> snippets = snippetService.getAllSnippets();
            System.out.println("Found " + snippets.size() + " snippets");
            model.addAttribute("snippets", snippets);
            return "snippets/list-snippets"; // Correct template path
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error loading snippets: " + e.getMessage());
            return "snippets/list-snippets";
        }
    }
    
    // New Snippet - Show create form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("snippet", new Snippet());
        return "snippets/create-snippet";
    }
    
    // New Snippet - Process creation
    @PostMapping("/create")
    public String createSnippet(@ModelAttribute Snippet snippet, Model model) {
        try {
            System.out.println("=== Creating new snippet ===");
            System.out.println("Title: " + snippet.getTitle());
            System.out.println("Language: " + snippet.getLanguage());
            
            // Temporary: Set a user (use first available user)
            Optional<User> firstUser = userService.getAllUsers().stream().findFirst();
            if (firstUser.isPresent()) {
                snippet.setUser(firstUser.get());
                System.out.println("User set: " + firstUser.get().getUsername());
            } else {
                // Create a default user if none exists
                User defaultUser = new User("default", "default@example.com", "password", "Default User");
                userService.createUser(defaultUser);
                snippet.setUser(defaultUser);
            }
            
            Snippet savedSnippet = snippetService.createSnippet(snippet);
            System.out.println("Snippet saved with ID: " + savedSnippet.getId());
            
            return "redirect:/snippets?success=created";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error creating snippet: " + e.getMessage());
            return "snippets/create-snippet";
        }
    }
    
    // Search functionality
    @GetMapping("/search")
    public String searchSnippets(@RequestParam String keyword, Model model) {
        try {
            System.out.println("=== Searching snippets with keyword: " + keyword + " ===");
            List<Snippet> snippets = snippetService.searchSnippetsByTitle(keyword);
            System.out.println("Found " + snippets.size() + " matching snippets");
            model.addAttribute("snippets", snippets);
            model.addAttribute("keyword", keyword);
            return "snippets/list-snippets";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/snippets?error=search";
        }
    }
    
    // Edit Snippet - Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Optional<Snippet> snippet = snippetService.getSnippetById(id);
            if (snippet.isPresent()) {
                model.addAttribute("snippet", snippet.get());
                return "snippets/edit-snippet";
            }
            return "redirect:/snippets?error=not_found";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/snippets?error=edit";
        }
    }
    
    // Update Snippet
    @PostMapping("/update/{id}")
    public String updateSnippet(@PathVariable Long id, @ModelAttribute Snippet snippetDetails) {
        try {
            Snippet updated = snippetService.updateSnippet(id, snippetDetails);
            if (updated != null) {
                return "redirect:/snippets?success=updated";
            }
            return "redirect:/snippets?error=not_found";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/snippets?error=update";
        }
    }
    
    // Delete Snippet
    @GetMapping("/delete/{id}")
    public String deleteSnippet(@PathVariable Long id) {
        try {
            snippetService.deleteSnippet(id);
            return "redirect:/snippets?success=deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/snippets?error=delete";
        }
    }
}