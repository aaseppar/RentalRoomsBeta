package ru.sepparalex.accomodrental.error;

public class hasNoUserIdByEmailException extends RuntimeException{
    public hasNoUserIdByEmailException(String message){
        super(message);
    }
}
