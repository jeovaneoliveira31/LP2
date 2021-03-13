package com.ifce.lp2.atividade.controller;

import com.ifce.lp2.atividade.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todorepo;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
      
    }

}
