package com.snippets.service;

import com.snippets.entity.Snippet;
import java.util.List;
import java.util.Optional;

public interface SnippetService {
	  String getAIAnalysis(Long snippetId);
	    String generateCodeExplanation(Long snippetId);
	    String optimizeSnippetCode(Long snippetId);
	    String debugSnippet(Long snippetId, String errorDescription);
    Snippet createSnippet(Snippet snippet);
    Optional<Snippet> getSnippetById(Long id);
    List<Snippet> getAllSnippets();
    List<Snippet> getSnippetsByUserId(Long userId);
    List<Snippet> getSnippetsByLanguage(String language);
    List<Snippet> searchSnippetsByTitle(String keyword);
    Snippet updateSnippet(Long id, Snippet snippetDetails);
    void deleteSnippet(Long id);
}