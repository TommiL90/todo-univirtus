package com.univirtus.todo.demo.todo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.univirtus.todo.demo.todo.dto.TodoResponse;
import com.univirtus.todo.demo.todo.entity.TodoEntity;

@Component
public class TodoMapper {

	public TodoResponse toResponse(TodoEntity entity) {
		return new TodoResponse(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getDueDate(),
				entity.getResponsible());
	}

	public List<TodoResponse> toResponse(List<TodoEntity> entities) {
		return entities.stream().map(this::toResponse).toList();
	}

}
