package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.error.*;
import ru.sepparalex.accomodrental.models.Rooms;
import ru.sepparalex.accomodrental.services.RoomsService;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rooms")
@Slf4j
public class RoomsController {
    private final RoomsService roomsService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getAll(){
        return roomsService.findAll();
    }

    @GetMapping("/city/")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByCity(@RequestParam("name") String name){
        return roomsService.findByCity(name);
     }
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundByCityName(NoRoomsByCityNameException ex){
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/country")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByCountryName(@RequestParam("name") String name){
        return  roomsService.findByCountryName(name);
    }
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundByCountryName(NoRoomsByCountryNameException ex){
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND);
    }


    @GetMapping("/rating/")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByRating(@RequestParam("rating") Integer rating){
        return roomsService.findByRating(rating);
    }
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundByRating(NoRoomsByRatingException ex){
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND);
    }


    @GetMapping("/lessOrEquals/")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByPriceLessOrEquals(@RequestParam("price") Integer price){
        return roomsService.findByPriceLessOrEquals(price);
    }
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundByPriceLess(NoRoomsByPriceLessException ex){
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND);
    }


    @GetMapping("/moreOrEquals/")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByPriceMoreOrEquals(@RequestParam("price") Integer price){
        return roomsService.findByPriceMoreOrEquals(price);
    }
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundByPriceMore(NoRoomsByPriceMoreException ex){
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND);
    }


    @GetMapping("/before")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByDateBefore(@RequestParam ("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date){
        return  roomsService.findBeforeDate(date);
    }
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundByBeforeTerm(NoRoomsByBeforeTermException ex){
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/after")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByDateAfter(@RequestParam ("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date){
        return roomsService.findAfterDate(date);
    }
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundByAfterTerm(NoRoomsByAfterTermException ex){
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND);
    }


    @GetMapping("/flagFree")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByFlagFree(){
        return  roomsService.findByFlagFree();
    }
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handlerNotFoundFreeRooms(NoRoomsByFlagFreeException ex){
        log.error(ex.getMessage());
        return new ErrorMessage(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

}
