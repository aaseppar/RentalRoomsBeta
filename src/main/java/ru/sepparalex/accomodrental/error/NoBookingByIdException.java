package ru.sepparalex.accomodrental.error;

public class NoBookingByIdException extends RuntimeException{
    public NoBookingByIdException(String message){
        super(message);
    }
}
