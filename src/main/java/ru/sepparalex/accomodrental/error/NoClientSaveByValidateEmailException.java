package ru.sepparalex.accomodrental.error;

public class NoClientSaveByValidateEmailException extends RuntimeException{
    public NoClientSaveByValidateEmailException(String message){
      super(message);
    }
}
