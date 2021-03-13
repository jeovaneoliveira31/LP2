package com.ifce.lp2.atividade.controller;

import java.util.List;

import com.ifce.lp2.atividade.model.Todo;
import com.ifce.lp2.atividade.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        }else {
            return new ResponseEntity<>("No Todos Avaliable", HttpStatus.NOT_FOUND);
        }
    }
}
