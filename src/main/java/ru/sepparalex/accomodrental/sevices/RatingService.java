package ru.sepparalex.accomodrental.sevices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.Rating;
import ru.sepparalex.accomodrental.repositories.RatingRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    public List<Rating> findAll(){
        return ratingRepository.findAll();
    }
}
