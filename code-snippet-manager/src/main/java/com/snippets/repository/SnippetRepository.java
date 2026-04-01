package com.snippets.repository;

import com.snippets.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    List<Snippet> findByUserId(Long userId);
    List<Snippet> findByLanguage(String language);
    List<Snippet> findByTitleContainingIgnoreCase(String keyword);
}