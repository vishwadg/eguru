package com.example.commonmodule.exceptions;

public class EmailAlreadyUsedException extends RuntimeException{
    public EmailAlreadyUsedException(){
        super("Email Already Used");
    }
}
