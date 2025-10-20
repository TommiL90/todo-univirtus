package com.univirtus.todo.demo.todo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univirtus.todo.demo.todo.dto.UpdateTodoRequest;
import com.univirtus.todo.demo.todo.entity.TodoEntity;
import com.univirtus.todo.demo.todo.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UpdateTodoService {

	private final TodoRepository todoRepository;

	public UpdateTodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Transactional
	public TodoEntity execute(UUID todoId, UpdateTodoRequest request) {
		TodoEntity todo = todoRepository.findById(todoId)
				.orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada para o id " + todoId));
		if (request.getTitle() != null) {
			todo.setTitle(request.getTitle());
		}
		if (request.getDescription() != null) {
			todo.setDescription(request.getDescription());
		}
		if (request.getDueDate() != null) {
			todo.setDueDate(request.getDueDate());
		}
		if (request.getResponsible() != null) {
			todo.setResponsible(request.getResponsible());
		}
		return todo;
	}

}
