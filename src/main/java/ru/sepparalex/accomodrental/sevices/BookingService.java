package ru.sepparalex.accomodrental.sevices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.repositories.BookingRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private  final BookingRepository bookingRepository;
    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

}
