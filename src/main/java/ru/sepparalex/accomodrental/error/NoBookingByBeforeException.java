package ru.sepparalex.accomodrental.error;

public class NoBookingByBeforeException extends RuntimeException{
    public NoBookingByBeforeException(String message){
        super(message);
    }
}
