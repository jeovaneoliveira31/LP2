package com.ifce.lp2.atividade.repository;

import com.ifce.lp2.atividade.model.Todo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository < Todo , String >{
    
}