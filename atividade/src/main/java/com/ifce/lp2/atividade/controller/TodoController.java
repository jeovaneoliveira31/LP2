package com.ifce.lp2.atividade.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ifce.lp2.atividade.model.Todo;
import com.ifce.lp2.atividade.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todorepo;

    @GetMapping("/Todos")
    public ResponseEntity<?> getAllTodos(){

        List<Todo> Todos = todorepo.findAll();

        if (Todos.size() > 0) {
            return new ResponseEntity<List<Todo>>(Todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Todos Avaliable", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Todos")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo){
        try {
            todo.setCreatedAT(new Date(System.currentTimeMillis()));
            todorepo.save(todo);
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
        Optional<Todo> todoOptinal = todorepo.findById(id);

        if (todoOptinal.isPresent()) {
            return new ResponseEntity<>(todoOptinal.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found with id"+id, HttpStatus.NOT_FOUND);
        }
        
    }

    @PutMapping("/Todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody Todo todo) {
        Optional<Todo> todoOptinal = todorepo.findById(id);

        if (todoOptinal.isPresent()) {
            Todo todoToSave = todoOptinal.get();
            todoToSave.setCompleted( todo.getCompleted() != null ? todo.getCompleted(): todoToSave.getCompleted());
            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo(): todoToSave.getTodo());
            todoToSave.setDescription(todo.getDescription() !=null ? todo.getDescription() : todoToSave.getDescription());
            todoToSave.setUpdatedAT(new Date(System.currentTimeMillis()));
            todorepo.save(todoToSave);
            return new ResponseEntity<>(todoToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found with id"+id, HttpStatus.NOT_FOUND);
        }
        
    }
}
