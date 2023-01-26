package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sepparalex.accomodrental.models.Rooms;
import ru.sepparalex.accomodrental.sevices.RoomsService;

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
}
