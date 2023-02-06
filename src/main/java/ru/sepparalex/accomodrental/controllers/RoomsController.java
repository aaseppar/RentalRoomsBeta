package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sepparalex.accomodrental.models.Rooms;
import ru.sepparalex.accomodrental.services.RoomsService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rooms")
public class RoomsController {
    private final RoomsService roomsService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getAll(){
        List<Rooms> rooms=roomsService.findAll();
         return rooms;
    }
    @GetMapping("/city/")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByCity(@RequestParam("name") String name){
        List<Rooms> rooms=  roomsService.findByCity(name);
        return rooms;
    }
    @GetMapping("/country")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByCountry(@RequestParam("name") String name){
        List<Rooms> rooms=  roomsService.findByCountryName(name);
        return rooms;
    }
    @GetMapping("/rating/")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByCity(@RequestParam("rating") Integer rating){
        List<Rooms> rooms=  roomsService.findByRating(rating);
        return rooms;
    }
    @GetMapping("/booking")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByCountry(@RequestParam("price") Integer price){
        List<Rooms> rooms=  roomsService.findByPrice(price);
        return rooms;
    }
    @GetMapping("/before")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByDateBefore(@RequestParam ("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date){
        List<Rooms> rooms=  roomsService.findBeforeDate(date);
        return rooms;
    }
    @GetMapping("/after")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByDateAfter(@RequestParam ("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date){
        List<Rooms> rooms=  roomsService.findAfterDate(date);
        return rooms;
    }
    @GetMapping("/flagFree")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByFlagFree(){
        List<Rooms> rooms=  roomsService.findByFlagFree();
        return rooms;
    }

}
