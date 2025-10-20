package com.univirtus.todo.demo.todo.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTodoRequest {

	@Size(max = 120, message = "Título deve ter até 120 caracteres")
	private String title;

	@Size(max = 500, message = "Descrição deve ter até 500 caracteres")
	private String description;

	@FutureOrPresent(message = "Data de entrega não pode ser anterior a hoje")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;

	@Size(max = 120, message = "Responsável deve ter até 120 caracteres")
	@Pattern(regexp = "^$|.*\\S.*", message = "Responsável não pode ser vazio")
	private String responsible;

}
