package com.ifce.lp2.atividade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import com.ifce.lp2.atividade.exeption.TodoCollectionExeption;
import com.ifce.lp2.atividade.model.Todo;
import com.ifce.lp2.atividade.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepo;


    @Override
    public void CreateTodo(Todo todo) throws ConstraintViolationException, TodoCollectionExeption{
        Optional<Todo> todoOptional = todoRepo.findByTodo(todo.getTodo());
        if (todoOptional.isPresent()) {
            throw new TodoCollectionExeption(TodoCollectionExeption.TodoAlreadyExists());
        } else {
            todo.setCreatedAT(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
        }
    }

    @Override
    public List<Todo> getAllTodos(){
       List<Todo> Todos = todoRepo.findAll();
       if(Todos.size()>0) {
           return Todos;
       } else {
        return new ArrayList<Todo>();
       }
    }

    @Override
    public Todo getSingleTodo(String id) throws TodoCollectionExeption{

        Optional<Todo> optionalTodo = todoRepo.findById(id);
        if (optionalTodo.isPresent()) {
            throw new TodoCollectionExeption(TodoCollectionExeption.NotFoundExeption(id));
        } else {
            return optionalTodo.get();
        }
    }

    @Override
    public void updateTodo(String id, Todo todo) throws TodoCollectionExeption{
        Optional<Todo> todoWithId = todoRepo.findById(id);
        Optional<Todo> todoWithSameName = todoRepo.findByTodo(todo.getTodo());

        if (todoWithId.isPresent()) {

            if (todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)) {
                throw new TodoCollectionExeption(TodoCollectionExeption.TodoAlreadyExists());
            } 

            Todo todoToUpdate = todoWithId.get();

            todoToUpdate.setTodo(todo.getTodo());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.getCompleted());
            todoToUpdate.setUpdatedAT(new Date(System.currentTimeMillis()));
            todoRepo.save(todoToUpdate);
        } else {
            throw new TodoCollectionExeption(TodoCollectionExeption.NotFoundExeption(id));
        }
    }

    @Override
    public void deleteTodoById(String id) throws TodoCollectionExeption{
        Optional<Todo> todoOptional = todoRepo.findById(id);
        if (!todoOptional.isPresent()) {
            throw new TodoCollectionExeption(TodoCollectionExeption.NotFoundExeption(id));
        } else {
            todoRepo.deleteById(id);
        }
    }
}