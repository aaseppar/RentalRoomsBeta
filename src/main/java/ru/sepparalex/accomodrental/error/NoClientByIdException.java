package ru.sepparalex.accomodrental.error;

public class NoClientByIdException extends  RuntimeException{
    public NoClientByIdException(String message){
        super(message);
    }
}
