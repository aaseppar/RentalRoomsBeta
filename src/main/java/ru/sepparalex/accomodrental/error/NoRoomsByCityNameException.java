package ru.sepparalex.accomodrental.error;

public class NoRoomsByCityNameException extends RuntimeException {
    public NoRoomsByCityNameException(String message){
        super(message);
    }

}
