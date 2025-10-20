package com.univirtus.todo.demo.todo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univirtus.todo.demo.todo.dto.CreateTodoRequest;
import com.univirtus.todo.demo.todo.dto.TodoResponse;
import com.univirtus.todo.demo.todo.dto.UpdateTodoRequest;
import com.univirtus.todo.demo.todo.mapper.TodoMapper;
import com.univirtus.todo.demo.todo.service.CreateTodoService;
import com.univirtus.todo.demo.todo.service.DeleteTodoService;
import com.univirtus.todo.demo.todo.service.GetTodoByIdService;
import com.univirtus.todo.demo.todo.service.ListTodosService;
import com.univirtus.todo.demo.todo.service.UpdateTodoService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

	private final CreateTodoService createTodoService;

	private final ListTodosService listTodosService;

	private final GetTodoByIdService getTodoByIdService;

	private final UpdateTodoService updateTodoService;

	private final DeleteTodoService deleteTodoService;

	private final TodoMapper todoMapper;

	public TodoController(CreateTodoService createTodoService, ListTodosService listTodosService,
			GetTodoByIdService getTodoByIdService, UpdateTodoService updateTodoService,
			DeleteTodoService deleteTodoService, TodoMapper todoMapper) {
		this.createTodoService = createTodoService;
		this.listTodosService = listTodosService;
		this.getTodoByIdService = getTodoByIdService;
		this.updateTodoService = updateTodoService;
		this.deleteTodoService = deleteTodoService;
		this.todoMapper = todoMapper;
	}

	@PostMapping
	public ResponseEntity<TodoResponse> create(@Valid @RequestBody CreateTodoRequest request) {
		var created = createTodoService.execute(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(todoMapper.toResponse(created));
	}

	@GetMapping
	public ResponseEntity<List<TodoResponse>> list() {
		var todos = listTodosService.execute();
		return ResponseEntity.ok(todoMapper.toResponse(todos));
	}

	@GetMapping("/{todoId}")
	public ResponseEntity<TodoResponse> getById(@PathVariable UUID todoId) {
		var todo = getTodoByIdService.execute(todoId);
		return ResponseEntity.ok(todoMapper.toResponse(todo));
	}

	@PutMapping("/{todoId}")
	public ResponseEntity<TodoResponse> update(@PathVariable UUID todoId,
			@Valid @RequestBody UpdateTodoRequest request) {
		var updated = updateTodoService.execute(todoId, request);
		return ResponseEntity.ok(todoMapper.toResponse(updated));
	}

	@DeleteMapping("/{todoId}")
	public ResponseEntity<Void> delete(@PathVariable UUID todoId) {
		deleteTodoService.execute(todoId);
		return ResponseEntity.noContent().build();
	}

}
