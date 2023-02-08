package ru.sepparalex.accomodrental.error;

public class NoClientSaveByEmailException extends RuntimeException{
    public NoClientSaveByEmailException(String message){
        super(message);
    }
}
