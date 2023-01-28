package ru.sepparalex.accomodrental.sevices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.models.Rooms;
import ru.sepparalex.accomodrental.repositories.RoomsRepository;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomsService {
    private final RoomsRepository roomsRepository;
    public List<Rooms> findAll(){
        return roomsRepository.findAll();
    }

    public List<Rooms> findByCity(String name) {
        return roomsRepository.findByNameIgnoreCaseOrderByNameAsc(name);
    }

    public List<Rooms> findByCountry(String name) {
        return roomsRepository.findByNameIgnoreCase(name);
    }

    public List<Rooms> findByRating(Integer rating) {
        return roomsRepository.findByRating(rating);
    }

    public List<Rooms> findByPrice(Integer price) {
        return roomsRepository.findByBookingPrice(price);
    }

    public List<Rooms> findBeforeDate(Date date) {
        return roomsRepository.findBeforeDate(date);
    }

    public List<Rooms> findAfterDate(Date date) {
        return roomsRepository.findAfterDate(date);
    }
}
