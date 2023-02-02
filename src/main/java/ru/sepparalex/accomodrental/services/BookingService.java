package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sepparalex.accomodrental.models.*;
import ru.sepparalex.accomodrental.repositories.BookingRepository;
import ru.sepparalex.accomodrental.repositories.CityRepository;
import ru.sepparalex.accomodrental.repositories.CountryRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {
    private  final BookingRepository bookingRepository;
    private  final CountryService countryService;
    private  final CityService cityService;
    private  final RoomsService roomsService;
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
    @Transactional
    public Booking save(Booking booking,int idRoom,int exist,String cityName,String countryName) {
        if(exist==0){

            Country country=new Country(countryName);
            country=countryService.save(country);
            City city= new City(cityName,country);
            city=cityService.save(city);
            booking.getClient().setCity(city);
            Rooms roomNew=new Rooms(3,1, booking.getClient(),city);
            roomNew=roomsService.save(roomNew);
            booking.setRoomsList(Collections.singletonList(roomNew));
            //booking.getRoomsList().add(roomNew);
            //booking.getClient().setListBooking(Collections.singletonList(booking));
            //booking.getClient().getListBooking().add(booking);
           }
        if(exist==1){
           booking.getRoomsList().get(idRoom).setFlagfree(1);

        }
        return  bookingRepository.save(booking);
    }
}
