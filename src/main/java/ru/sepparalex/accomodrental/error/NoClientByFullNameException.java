package ru.sepparalex.accomodrental.error;

public class NoClientByFullNameException extends RuntimeException{
    public NoClientByFullNameException(String message){
        super(message);
    }
}
