package ru.sepparalex.accomodrental.error;

public class NoClientSaveByStatusADMIN extends RuntimeException{
    public NoClientSaveByStatusADMIN(String message){
        super(message);
    }
}
