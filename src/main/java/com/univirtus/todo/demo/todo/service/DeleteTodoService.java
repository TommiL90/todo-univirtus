package com.univirtus.todo.demo.todo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univirtus.todo.demo.todo.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DeleteTodoService {

	private final TodoRepository todoRepository;

	public DeleteTodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Transactional
	public void execute(UUID todoId) {
		if (!todoRepository.existsById(todoId)) {
			throw new EntityNotFoundException("Tarefa não encontrada para o id " + todoId);
		}
		todoRepository.deleteById(todoId);
	}

}
