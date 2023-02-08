package ru.sepparalex.accomodrental.error;

public class NoRoomsByRatingException extends RuntimeException{
   public NoRoomsByRatingException(String message){
        super(message);
    }
}
