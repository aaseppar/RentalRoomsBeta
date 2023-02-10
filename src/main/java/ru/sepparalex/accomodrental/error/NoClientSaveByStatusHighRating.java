package ru.sepparalex.accomodrental.error;

public class NoClientSaveByStatusHighRating extends RuntimeException{
    public NoClientSaveByStatusHighRating(String message){
        super(message);
    }
}
