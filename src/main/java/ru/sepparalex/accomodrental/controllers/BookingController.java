package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.sevices.BookingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {
 private final BookingService bookingService;
 @GetMapping
    public List<Booking>  getAll(){
   List<Booking> booking=bookingService.findAll();
     booking.forEach(b -> System.out.println(b));
     return booking;
 }

}
