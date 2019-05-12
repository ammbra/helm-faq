package com.example.faq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.faq.exception.ResourceNotFoundException;
import com.example.faq.model.Answer;
import com.example.faq.repository.AnswerRepository;
import com.example.faq.repository.QuestionRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Answer> getAllAnswers() {
		return answerRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{questionId}")
	public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
		return answerRepository.findByQuestionId(questionId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{questionId}")
	public Answer addAnswer(@PathVariable Long questionId, @Valid @RequestBody Answer answer) {
		return questionRepository.findById(questionId).map(question -> {
			answer.setQuestion(question);
			return answerRepository.save(answer);
		}).orElseThrow(() -> new ResourceNotFoundException(
				new StringBuffer("Question with id ").append(questionId).append("not found").toString()));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{answerId}/questions/{questionId}")
	public Answer updateAnswer(@PathVariable Long answerId, @PathVariable Long questionId,
			@Valid @RequestBody Answer request) {
		if (!questionRepository.existsById(questionId)) {
			throw new ResourceNotFoundException(
					new StringBuffer("Question with id ").append(questionId).append("not found").toString());
		}

		return answerRepository.findById(answerId).map(answer -> {
			answer.setText(request.getText());
			return answerRepository.save(answer);
		}).orElseThrow(() -> new ResourceNotFoundException(
				new StringBuffer("Answer with id ").append(answerId).append("not found").toString()));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{answerId}/question/{questionId}")
	public ResponseEntity<?> deleteAnswer(@PathVariable Long answerId, @PathVariable Long questionId) {
		if (!questionRepository.existsById(questionId)) {
			throw new ResourceNotFoundException(
					new StringBuffer("Question with id ").append(questionId).append("not found").toString());
		}

		return answerRepository.findById(answerId).map(answer -> {
			answerRepository.delete(answer);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(
				new StringBuffer("Answer with id ").append(answerId).append("not found").toString()));

	}
}
