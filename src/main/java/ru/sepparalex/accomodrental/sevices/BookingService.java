package ru.sepparalex.accomodrental.sevices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.Booking;
import ru.sepparalex.accomodrental.repositories.BookingRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {
    private  final BookingRepository bookingRepository;
    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }
    public Booking findById(int id){
        Optional <Booking> res=bookingRepository.findById(id);
        return res.orElseThrow();
    }

    public List<Booking> findBeforeDate(Date value) {
        return bookingRepository.findByEndtermBefore(value);
    }


    public List<Booking> findAfterDate(Date value) {
       return bookingRepository.findByBegintermAfter(value);

    }
}
