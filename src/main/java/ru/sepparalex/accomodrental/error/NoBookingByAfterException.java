package ru.sepparalex.accomodrental.error;

public class NoBookingByAfterException extends RuntimeException{
    public NoBookingByAfterException(String message){
        super(message);
    }
}
