package ru.sepparalex.accomodrental.error;

public class NoClientByLoginAndEmailException extends  RuntimeException{
    public NoClientByLoginAndEmailException(String message){
        super(message);
    }
}
