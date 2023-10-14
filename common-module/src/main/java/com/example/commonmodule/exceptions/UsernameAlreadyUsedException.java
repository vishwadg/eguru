package com.example.commonmodule.exceptions;

public class UsernameAlreadyUsedException extends RuntimeException{
    public UsernameAlreadyUsedException(){
        super("Username Already Used");
    }
}
