package com.univirtus.todo.demo.todo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univirtus.todo.demo.todo.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, UUID> {

}
