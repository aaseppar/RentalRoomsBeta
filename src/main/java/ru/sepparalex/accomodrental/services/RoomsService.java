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
        List<Rooms> roomsList=roomsRepository.findByNameIgnoreCaseOrderByNameAsc(name);
        if(roomsList.size()==0){
          throw  new NoRoomsByCityNameException("That City doesn't contain any rooms");
        }
        else return roomsList;

    }

    public List<Rooms> findByCountryName(String name) {
        List<Rooms> roomsList=roomsRepository.findByCountryNameIgnoreCase(name);
        if(roomsList.size()==0){
           throw  new NoRoomsByCountryNameException("That Country doesn't contain any rooms");
        }
       else return roomsList;

    }

    public List<Rooms> findByRating(Integer rating) {
        List<Rooms> roomsList=roomsRepository.findByRating(rating);
       if(roomsList.size()==0){
           throw new NoRoomsByRatingException("There are no apartments with this rating");
       }
       else return roomsList;
    }

    public List<Rooms> findByPriceLessOrEquals(Integer price) {
        List<Rooms> roomsList=roomsRepository.findByBookingPriceLessOrEquals(price);
        if(roomsList.size()==0){
            throw new NoRoomsByPriceLessException("There aren't apartments with a price less or equals than this");
        }
        else return roomsList;
    }

    public List<Rooms> findByPriceMoreOrEquals(Integer price) {
        List<Rooms> roomsList=roomsRepository.findByBookingPriceMoreOrEquals(price);
        if(roomsList.size()==0){
            throw new NoRoomsByPriceMoreException("There aren't apartments with a price more or equals than this");
        }
        else return roomsList;
    }

    public List<Rooms> findBeforeDate(Date date) {
        List<Rooms> roomsList=roomsRepository.findBeforeDate(date);
        if(roomsList.size()==0) {
        throw new NoRoomsByBeforeTermException("There aren't apartments with a date before that");
        }
        else return roomsList;
    }

    public List<Rooms> findAfterDate(Date date) {
        List<Rooms> roomsList=roomsRepository.findAfterDate(date);
        if(roomsList.size()==0){
            throw new NoRoomsByAfterTermException("There aren't apartments with a date after that");
        }
       else return roomsList;
    }

    public Rooms save(Rooms rooms){
        return  roomsRepository.save(rooms);
    };

    public Rooms findByClientId(int id) {
        return roomsRepository.findByClientId(id);
    }
    public Rooms findByRoomsId(int roomsId){
        Rooms room=roomsRepository.findByRoomsId(roomsId);
        if(room==null) {
            throw new EntityNotFoundException();
        }
      else return room;
    }

    public List<Rooms> findByFlagFree() {
        List<Rooms> roomsList=roomsRepository.findByFlagFree();
        if(roomsList.size()==0) {
            throw new NoRoomsByFlagFreeException("There aren't free apartments");
        }
       else return roomsList;
    }
}
