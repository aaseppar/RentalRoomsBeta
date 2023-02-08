package ru.sepparalex.accomodrental.error;

public class NoRoomsByPriceLessException extends RuntimeException{
    public NoRoomsByPriceLessException(String message){
        super(message);
    }
}
