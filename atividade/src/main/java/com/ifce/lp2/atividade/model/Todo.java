package com.ifce.lp2.atividade.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Todos")

public class Todo {

    @id
    private String id;
    private String todo;
    private String description;
    private boolean completed;
    private Date createdAT;
    private Date updatedAT;

}
