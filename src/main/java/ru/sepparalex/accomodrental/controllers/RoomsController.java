package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.models.Rooms;
import ru.sepparalex.accomodrental.sevices.RoomsService;

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
    @GetMapping("/coutry")
    @PreAuthorize("hasAnyAuthority('rooms:read')")
    public List<Rooms> getByCountry(@RequestParam("name") String name){
        List<Rooms> rooms=  roomsService.findByCountry(name);
        return rooms;
    }
}
