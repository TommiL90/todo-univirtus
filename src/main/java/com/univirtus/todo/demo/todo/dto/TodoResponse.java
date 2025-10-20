package com.univirtus.todo.demo.todo.dto;

import java.time.LocalDate;
import java.util.UUID;

public record TodoResponse(UUID id, String title, String description, LocalDate dueDate, String responsible) {
}
