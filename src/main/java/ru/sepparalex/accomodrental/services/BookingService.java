package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sepparalex.accomodrental.models.*;
import ru.sepparalex.accomodrental.repositories.BookingRepository;
import ru.sepparalex.accomodrental.repositories.CityRepository;
import ru.sepparalex.accomodrental.repositories.CountryRepository;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private  final ClientService clientService;
    private  final RoomsService roomsService;
    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }
    public Booking findById(int id){
        return bookingRepository.findById(id).orElseThrow();

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
    @Transactional
    public Rooms takeBooking(int idNewClient,int roomsId) throws ParseException {
    Rooms rooms =roomsService.findByRoomsId(roomsId);
        Client clientNewLessee=clientService.findById(idNewClient);
        Booking booking= new Booking(new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-04"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-24"),23000,clientNewLessee);
        Booking booking1= bookingRepository.save(booking);
        rooms.setBooking(booking1);
        rooms.setFlagfree(0);
      return roomsService.save(rooms);

    }
}
