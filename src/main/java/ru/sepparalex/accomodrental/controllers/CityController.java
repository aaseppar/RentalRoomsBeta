package ru.sepparalex.accomodrental.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.models.City;
import ru.sepparalex.accomodrental.services.CityService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/city/")
public class CityController {
    private final CityService cityService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('city:read')")
    public List<City> getAll(){
        List<City> city=cityService.findAll();
        return city;
    }
    @GetMapping("{name}")
    @PreAuthorize("hasAnyAuthority('city:read')")
    public City getByName(@PathVariable("name") String name){
        return cityService.findByName(name);
    }
    @PostMapping ("{name}")
    @PreAuthorize("hasAnyAuthority('city:write')")
    public City createCity(@RequestBody City city){
       return cityService.save(city);

    }


}