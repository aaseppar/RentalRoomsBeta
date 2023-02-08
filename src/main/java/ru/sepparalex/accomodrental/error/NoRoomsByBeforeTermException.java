package ru.sepparalex.accomodrental.error;

public class NoRoomsByBeforeTermException extends RuntimeException{
   public NoRoomsByBeforeTermException(String message){
        super(message);
    }
}
