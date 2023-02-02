package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.models.Status;
import ru.sepparalex.accomodrental.services.BookingService;

import java.util.Date;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {
 private final BookingService bookingService;
 @GetMapping
 //@PreAuthorize("hasAnyAuthority('booking:read')")
    public List<Booking>  getAll(){
   List<Booking> booking=bookingService.findAll();
   return booking;
 }
    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('booking:read')")
    public Booking getById(@PathVariable("id") int id){
    return bookingService.findById(id);

    }
    @GetMapping("/before")
    @PreAuthorize("hasAnyAuthority('booking:read')")
    public List<Booking> getByDateBefore(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Booking> booking=  bookingService.findBeforeDate(value);
        return booking;
    }

    @GetMapping("/after")
    @PreAuthorize("hasAnyAuthority('booking:read')")
    public List<Booking> getByDateAfter(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Booking> booking=  bookingService.findAfterDate(value);
        return booking;
    }
    @PostMapping("{id}/new/{idRoom}/{exist}/{cityName}/{countryName}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('booking:write')")
    public Booking createBooking(@PathVariable("id") int id,@PathVariable int exist,
                                 @PathVariable int idRoom,
                                 @PathVariable String cityName,
                                 @PathVariable String countryName,
                                 @RequestBody Booking booking){
       return bookingService.save(booking,idRoom,exist,cityName,countryName);

    }
}
