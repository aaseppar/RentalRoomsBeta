package ru.sepparalex.accomodrental.error;

public class NoRoomsByAfterTermException extends RuntimeException{
    public NoRoomsByAfterTermException(String message){
       super(message);
    }
}
