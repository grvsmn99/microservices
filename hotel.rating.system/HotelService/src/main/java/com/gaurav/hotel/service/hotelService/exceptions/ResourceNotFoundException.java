package com.gaurav.hotel.service.hotelService.exceptions;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (){
        super("Resource Not Found !!!");
    }
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
