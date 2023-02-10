package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.error.NoBookingByAfterException;
import ru.sepparalex.accomodrental.error.NoBookingByBeforeException;
import ru.sepparalex.accomodrental.error.NoBookingByIdException;
import ru.sepparalex.accomodrental.error.NoBookingSaveByClientIdException;
import ru.sepparalex.accomodrental.models.*;
import ru.sepparalex.accomodrental.repositories.BookingRepository;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        return bookingRepository.findById(id).orElseThrow(()->new NoBookingByIdException("There aren't Booking with that Id"));
    }
    public List<Booking> findBeforeDate(Date value) {
        List<Booking> bookingList= bookingRepository.findByEndtermBefore(value);
      if(bookingList.size()==0){
          throw new NoBookingByBeforeException("There aren't Booking before this date");
      }
      else return bookingList;
    }
    public List<Booking> findAfterDate(Date value) {
        List<Booking> bookingList= bookingRepository.findByBegintermAfter(value);
        if(bookingList.size()==0){
            throw new NoBookingByAfterException("There aren't Booking after this date");
        }
        else return bookingList;
    }
    @Transactional
    public Booking save(String login,String email,Booking booking,int idRoom,int exist,String cityName,String countryName) {
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
        if(!(clientService.findByLoginAndEmail(login,email).getStatus().equals(Status.BANNED))){
        return  bookingRepository.save(booking);
    }
        else{
            throw new NoBookingSaveByClientIdException("This Client have status BANNED !!!");
        }
    }
    @Transactional
    public Rooms takeBooking(String newClientLogin,String newClientEmail,int roomsId, Date begTerm,Date endTerm,int price) {
    Rooms rooms =roomsService.findByRoomsId(roomsId);
        Client clientNewLessee=clientService.findByLoginAndEmail(newClientLogin,newClientEmail);
        Booking booking= new Booking(begTerm,endTerm,price,clientNewLessee);
        Booking booking1= bookingRepository.save(booking);
        rooms.setBooking(booking1);
        rooms.setFlagfree(0);
        if(!(clientService.findByLoginAndEmail(newClientLogin,newClientEmail).getStatus().equals(Status.BANNED))){
        return roomsService.save(rooms);
        }
        else{
            throw new NoBookingSaveByClientIdException("This Client have status BANNED !!!");
        }
    }
}
