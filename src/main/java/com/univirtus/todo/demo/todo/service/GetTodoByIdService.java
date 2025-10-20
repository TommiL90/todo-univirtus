package com.univirtus.todo.demo.todo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univirtus.todo.demo.todo.entity.TodoEntity;
import com.univirtus.todo.demo.todo.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GetTodoByIdService {

	private final TodoRepository todoRepository;

	public GetTodoByIdService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Transactional(readOnly = true)
	public TodoEntity execute(UUID todoId) {
		return todoRepository.findById(todoId)
				.orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada para o id " + todoId));
	}

}
