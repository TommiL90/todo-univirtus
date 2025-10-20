package com.univirtus.todo.demo.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univirtus.todo.demo.todo.entity.TodoEntity;
import com.univirtus.todo.demo.todo.repository.TodoRepository;

@Service
public class ListTodosService {

	private final TodoRepository todoRepository;

	public ListTodosService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Transactional(readOnly = true)
	public List<TodoEntity> execute() {
		return todoRepository.findAll();
	}

}
