package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.error.*;
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
    public Booking findOwnBooking(String login,String email){
        Booking booking= bookingRepository.findOwnBooking(login,email);
        if (booking==null) throw new NoBookingByIdException("There aren't Booking with those login and email");
        else return booking;
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
    public Booking save(String login,String email,Booking booking,int idRoom,
                        int exist,String cityName,String countryName) {
        if(exist==0){ // Make new booking with new rooms
            City city1=null;
            if((cityService.findByName(cityName)==null)&&(countryService.findByName(countryName)==null)) {// Not exist that city and country
            Country country=new Country(countryName);
            country=countryService.save(country);
            City city= new City(cityName,country);
            city1=cityService.save(city);
            }
            else if((cityService.findByName(cityName)==null)&&(countryService.findByName(countryName)!=null)) { //city not exist bat country exist
                Country country=countryService.findByName(countryName);
                City city= new City(cityName,country);
                city1=cityService.save(city);
            }
            else if((cityService.findByName(cityName)!=null)&&(countryService.findByName(countryName)!=null)) {//city exist and country exist
                city1=cityService.findByName(cityName);
            }

            booking.getClient().setCity(city1);
            Rooms roomNew=new Rooms(3,1, booking.getClient(),city1);
            roomNew=roomsService.save(roomNew);
            booking.setRoomsList(Collections.singletonList(roomNew));
        }
        if(exist==1){ // Rooms already in booking and making free
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
        if(begTerm.after(endTerm)){
            throw new NoTakeBookingByWrongDateException("Those dates input is wrong!");
        }

        if(!(clientService.findByLoginAndEmail(newClientLogin,newClientEmail).getStatus().equals(Status.BANNED))){
        return roomsService.save(rooms);
        }
        else{
            throw new NoBookingSaveByClientIdException("This Client have status BANNED !!!");
        }
    }
}
