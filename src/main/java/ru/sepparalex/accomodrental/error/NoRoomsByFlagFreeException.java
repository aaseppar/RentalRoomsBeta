package ru.sepparalex.accomodrental.error;

public class NoRoomsByFlagFreeException extends RuntimeException{
    public NoRoomsByFlagFreeException(String message){
        super(message);
    }
}
