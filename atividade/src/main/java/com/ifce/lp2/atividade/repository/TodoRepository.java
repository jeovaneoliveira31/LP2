package com.ifce.lp2.atividade.repository;

import java.util.Optional;

import com.ifce.lp2.atividade.model.Todo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository < Todo , String >{

    @Query("{'todo' : ?0}")
    Optional<Todo> findByTodo(String todo);
   
}