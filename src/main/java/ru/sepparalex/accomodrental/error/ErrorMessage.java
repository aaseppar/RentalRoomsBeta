package ru.sepparalex.accomodrental.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
public class ErrorMessage {
    private String message;
     private LocalDateTime timestamp;
     private HttpStatus status;

    public ErrorMessage(String message,HttpStatus status ) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status=status;
    }
}
