package ru.sepparalex.accomodrental.error;

public class NoTakeBookingByWrongDateException extends RuntimeException{
    public NoTakeBookingByWrongDateException(String message){
        super(message);
    }
}
