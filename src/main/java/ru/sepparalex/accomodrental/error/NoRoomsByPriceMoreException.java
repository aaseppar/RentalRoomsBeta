package ru.sepparalex.accomodrental.error;

public class NoRoomsByPriceMoreException extends RuntimeException{
    public NoRoomsByPriceMoreException(String message){
        super(message);
    }
}
