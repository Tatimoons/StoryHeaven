package com.example.storyheaven.repository;

import com.example.storyheaven.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
