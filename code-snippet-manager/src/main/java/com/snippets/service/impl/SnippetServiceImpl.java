package com.snippets.service.impl;

import com.snippets.entity.Snippet;
import com.snippets.repository.SnippetRepository;
import com.snippets.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SnippetServiceImpl implements SnippetService {
    
    @Autowired
    private SnippetRepository snippetRepository;
    
    @Override
    public Snippet createSnippet(Snippet snippet) {
        return snippetRepository.save(snippet);
    }
    
    @Override
    public Optional<Snippet> getSnippetById(Long id) {
        return snippetRepository.findById(id);
    }
    
    @Override
    public List<Snippet> getAllSnippets() {
        return snippetRepository.findAll();
    }
    
    @Override
    public List<Snippet> getSnippetsByUserId(Long userId) {
        return snippetRepository.findByUserId(userId);
    }
    
    @Override
    public List<Snippet> getSnippetsByLanguage(String language) {
        return snippetRepository.findByLanguage(language);
    }
    
    @Override
    public List<Snippet> searchSnippetsByTitle(String keyword) {
        return snippetRepository.findByTitleContainingIgnoreCase(keyword);
    }
    
    @Override
    public Snippet updateSnippet(Long id, Snippet snippetDetails) {
        Optional<Snippet> snippetOptional = snippetRepository.findById(id);
        if (snippetOptional.isPresent()) {
            Snippet snippet = snippetOptional.get();
            snippet.setTitle(snippetDetails.getTitle());
            snippet.setDescription(snippetDetails.getDescription());
            snippet.setCode(snippetDetails.getCode());
            snippet.setLanguage(snippetDetails.getLanguage());
            return snippetRepository.save(snippet);
        }
        return null;
    }
    
    @Override
    public void deleteSnippet(Long id) {
        snippetRepository.deleteById(id);
    }

	@Override
	public String getAIAnalysis(Long snippetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateCodeExplanation(Long snippetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String optimizeSnippetCode(Long snippetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String debugSnippet(Long snippetId, String errorDescription) {
		// TODO Auto-generated method stub
		return null;
	}
}
