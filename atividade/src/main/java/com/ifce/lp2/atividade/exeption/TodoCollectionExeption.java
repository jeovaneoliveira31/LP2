package com.ifce.lp2.atividade.exeption;

public class TodoCollectionExeption extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public TodoCollectionExeption(String message){
        super(message);
    }

    public static String NotFoundExeption(String id) {
        return "Todo with ID "+id+" not found";
    }

    public static String TodoAlreadyExists() {
        return "Todo given name already exists";
    }

}
