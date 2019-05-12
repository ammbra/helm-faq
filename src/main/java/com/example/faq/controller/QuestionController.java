package com.example.faq.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.faq.exception.ResourceNotFoundException;
import com.example.faq.model.Question;
import com.example.faq.repository.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Page<Question> getQuestions(Pageable pageable) {
		return questionRepository.findAll(pageable);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{questionId}")
	public Question getQuestionById(@PathVariable Long questionId) {
		return questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException(
				new StringBuffer("Question with id ").append(questionId).append("not found").toString()));
	}

	@RequestMapping(method = RequestMethod.POST)
	public Question createQuestion(@Valid @RequestBody Question question) {
		return questionRepository.save(question);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{questionId}")
	public Question updateQuestion(@PathVariable Long questionId, @Valid @RequestBody Question request) {
		return questionRepository.findById(questionId).map(question -> {
			question.setText(request.getText());
			return questionRepository.save(question);
		}).orElseThrow(() -> new ResourceNotFoundException(
				new StringBuffer("Question with id ").append(questionId).append("not found").toString()));

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
		return questionRepository.findById(questionId).map(question -> {
			questionRepository.delete(question);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				new StringBuffer("Question with id ").append(questionId).append("not found").toString()));
	}
}
