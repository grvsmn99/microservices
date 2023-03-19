package com.gaurav.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not Found On Server!!!");
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
