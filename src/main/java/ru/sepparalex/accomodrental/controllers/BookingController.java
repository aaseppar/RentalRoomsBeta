package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.sevices.BookingService;

import java.util.Date;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {
 private final BookingService bookingService;
 @GetMapping
    public List<Booking>  getAll(){
   List<Booking> booking=bookingService.findAll();
    // booking.forEach(b -> System.out.println(b));
     return booking;
 }
    @GetMapping("{id}")
    public Booking getById(@PathVariable("id") int id){
        return bookingService.findById(id);

    }
    @GetMapping("/before")
    public List<Booking> getByDateBefore(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Booking> booking=  bookingService.findBeforeDate(value);
        return booking;
    }

    @GetMapping("/after")
    public List<Booking> getByDateAfter(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Booking> booking=  bookingService.findAfterDate(value);
        return booking;
    }
}
