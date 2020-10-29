package com.example.demo.Exceptions;

public class ResourceNotFound extends RuntimeException{
    private static final long serialVersionUID=1;

    public ResourceNotFound(String msg){
        super(msg);
    }

}
