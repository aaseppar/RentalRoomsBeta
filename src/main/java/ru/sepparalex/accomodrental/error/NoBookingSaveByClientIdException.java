package ru.sepparalex.accomodrental.error;

public class NoBookingSaveByClientIdException extends RuntimeException{
    public NoBookingSaveByClientIdException(String message){
        super(message);
    }
}
