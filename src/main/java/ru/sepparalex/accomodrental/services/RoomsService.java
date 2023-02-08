package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.error.*;
import ru.sepparalex.accomodrental.models.Rooms;
import ru.sepparalex.accomodrental.repositories.RoomsRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomsService {
    private final RoomsRepository roomsRepository;
    public List<Rooms> findAll(){
        return roomsRepository.findAll();
    }

    public List<Rooms> findByCity(String name) {

        if((roomsRepository.findByNameIgnoreCaseOrderByNameAsc(name)).size()==0){
          throw  new NoRoomsByCityNameException("That City doesn't contain any rooms");
        }
        else return  roomsRepository.findByNameIgnoreCaseOrderByNameAsc(name);

    }

    public List<Rooms> findByCountryName(String name) {
        if((roomsRepository.findByCountryNameIgnoreCase(name)).size()==0){
           throw  new NoRoomsByCountryNameException("That Country doesn't contain any rooms");
        }
       else return roomsRepository.findByCountryNameIgnoreCase(name);

    }

    public List<Rooms> findByRating(Integer rating) {
       if(roomsRepository.findByRating(rating).size()==0){
           throw new NoRoomsByRatingException("There are no apartments with this rating");
       }
       else return roomsRepository.findByRating(rating);
    }

    public List<Rooms> findByPriceLessOrEquals(Integer price) {
        if(roomsRepository.findByBookingPriceLessOrEquals(price).size()==0){
            throw new NoRoomsByPriceLessException("There aren't apartments with a price less or equals than this");
        }
        else return roomsRepository.findByBookingPriceLessOrEquals(price);
    }

    public List<Rooms> findByPriceMoreOrEquals(Integer price) {
        if(roomsRepository.findByBookingPriceMoreOrEquals(price).size()==0){
            throw new NoRoomsByPriceMoreException("There aren't apartments with a price more or equals than this");
        }
        else return roomsRepository.findByBookingPriceMoreOrEquals(price);
    }

    public List<Rooms> findBeforeDate(Date date) {
        if(roomsRepository.findBeforeDate(date).size()==0) {
            throw new NoRoomsByBeforeTermException("There aren't apartments with a date before that");
        }
        else return roomsRepository.findBeforeDate(date);
    }

    public List<Rooms> findAfterDate(Date date) {
        if(roomsRepository.findAfterDate(date).size()==0){
            throw new NoRoomsByAfterTermException("There aren't apartments with a date after that");
        }
       else return roomsRepository.findAfterDate(date);
    }

    public Rooms save(Rooms rooms){
        return  roomsRepository.save(rooms);
    };

    public Rooms findByClientId(int id) {
        return roomsRepository.findByClientId(id);
    }
    public Rooms findByRoomsId(int roomsId){
        if(roomsRepository.findByRoomsId(roomsId)==null) {
            throw new EntityNotFoundException();
        }
      else return roomsRepository.findByRoomsId(roomsId);
    }

    public List<Rooms> findByFlagFree() {
        if(roomsRepository.findByFlagFree().size()==0) {
            throw new NoRoomsByFlagFreeException("There aren't free apartments");
        }
       else return roomsRepository.findByFlagFree();
    }
}
