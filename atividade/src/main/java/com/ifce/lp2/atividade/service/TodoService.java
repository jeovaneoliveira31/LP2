package com.ifce.lp2.atividade.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.ifce.lp2.atividade.exeption.TodoCollectionExeption;
import com.ifce.lp2.atividade.model.Todo;

public interface TodoService {
    
    public void CreateTodo(Todo todo) throws ConstraintViolationException, TodoCollectionExeption;

    public List<Todo> getAllTodos();

    public Todo getSingleTodo(String id) throws TodoCollectionExeption;
   
    public void updateTodo(String id, Todo todo) throws TodoCollectionExeption;

    public void deleteTodoById(String id) throws TodoCollectionExeption;
}
