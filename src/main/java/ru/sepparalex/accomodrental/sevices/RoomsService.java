package ru.sepparalex.accomodrental.sevices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.Rooms;
import ru.sepparalex.accomodrental.repositories.RoomsRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomsService {
    private final RoomsRepository roomsRepository;
    public List<Rooms> findAll(){
        return roomsRepository.findAll();
    }
}
