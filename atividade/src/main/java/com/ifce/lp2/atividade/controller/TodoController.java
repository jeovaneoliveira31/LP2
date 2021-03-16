package com.ifce.lp2.atividade.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.ifce.lp2.atividade.exeption.TodoCollectionExeption;
import com.ifce.lp2.atividade.model.Todo;
import com.ifce.lp2.atividade.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {


    @Autowired
    private TodoService todoService;

    @GetMapping("/Todos")
    public ResponseEntity<?> getAllTodos(){

        List<Todo> Todos = todoService.getAllTodos();
        return new ResponseEntity<>(Todos, Todos.size()> 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Todos")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo){
        try {
            todoService.CreateTodo(todo);
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        } catch(TodoCollectionExeption e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/Todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/Todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Todo todo) {
        try {
            todoService.updateTodo(id, todo);
            return new ResponseEntity<>("Update todo with id "+id, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionExeption e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }  
    }

    @DeleteMapping("/Todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        try {
            todoService.deleteTodoById(id);
            return new ResponseEntity<>( "Sucessfully deleted with id "+id, HttpStatus.OK);

        } catch (TodoCollectionExeption e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}