package ru.sepparalex.accomodrental.error;

public class NoRoomsByCountryNameException extends RuntimeException{
   public NoRoomsByCountryNameException(String message){
        super(message);
    }
}
