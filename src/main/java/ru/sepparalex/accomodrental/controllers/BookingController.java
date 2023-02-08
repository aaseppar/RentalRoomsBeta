package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.error.*;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.models.Rooms;
import ru.sepparalex.accomodrental.services.BookingService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
@Slf4j
public class BookingController {
 private final BookingService bookingService;
  @GetMapping
  @PreAuthorize("hasAnyAuthority('booking:read')")
    public List<Booking>  getAll(){
   List<Booking> booking=bookingService.findAll();
   return booking;
 }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('booking:write')")
    public Booking getById(@PathVariable("id") int id){
    return bookingService.findById(id);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoBookinByID(NoBookingByIdException exc){
     log.error(exc.getMessage());
    return new ErrorMessage(exc.getMessage(),HttpStatus.NOT_FOUND);
   }


    @GetMapping("/before")
    @PreAuthorize("hasAnyAuthority('booking:read')")
    public List<Booking> getByDateBefore(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Booking> booking=  bookingService.findBeforeDate(value);
        return booking;
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoBookinByBefore(NoBookingByBeforeException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/after")
    @PreAuthorize("hasAnyAuthority('booking:read')")
    public List<Booking> getByDateAfter(@RequestParam ("value") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date value){
        List<Booking> booking=  bookingService.findAfterDate(value);
        return booking;
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoBookinByAfter(NoBookingByAfterException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.NOT_FOUND);
    }

    @PostMapping("{id}/new/{idRoom}/{exist}/{cityName}/{countryName}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('booking:write')")
    public Booking createBooking(@PathVariable("id") int id,@PathVariable int exist,
                                 @PathVariable int idRoom,
                                 @PathVariable String cityName,
                                 @PathVariable String countryName,
                                 @RequestBody Booking booking){
       return bookingService.save(id,booking,idRoom,exist,cityName,countryName);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoBookingSaveByClientId(NoBookingSaveByClientIdException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.NOT_FOUND);
    }

    @PostMapping("{id}/get/")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) or hasAuthority('booking:write')")
    public Rooms takeBooking(@PathVariable("id") int id,@RequestParam ("roomsId") int roomsId)
            throws ParseException {return bookingService.takeBooking(id,roomsId);
    }
}
