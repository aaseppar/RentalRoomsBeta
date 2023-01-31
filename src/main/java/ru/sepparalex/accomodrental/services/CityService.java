package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.City;
import ru.sepparalex.accomodrental.repositories.CityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public City findByName(String name) {
       return cityRepository.findByName(name);
    }


    public City save(City city) {
        return cityRepository.save(city);
    }
}
