package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.Country;
import ru.sepparalex.accomodrental.repositories.CountryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    public List<Country> findAll(){
        return countryRepository.findAll();
    }
}
