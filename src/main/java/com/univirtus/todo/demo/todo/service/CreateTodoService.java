package com.univirtus.todo.demo.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univirtus.todo.demo.todo.dto.CreateTodoRequest;
import com.univirtus.todo.demo.todo.entity.TodoEntity;
import com.univirtus.todo.demo.todo.repository.TodoRepository;

@Service
public class CreateTodoService {

	private final TodoRepository todoRepository;

	public CreateTodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Transactional
	public TodoEntity execute(CreateTodoRequest request) {
		TodoEntity todo = new TodoEntity();
		todo.setTitle(request.getTitle());
		todo.setDescription(request.getDescription());
		todo.setDueDate(request.getDueDate());
		todo.setResponsible(request.getResponsible());
		return todoRepository.save(todo);
	}

}
