package com.example.faq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.faq.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
