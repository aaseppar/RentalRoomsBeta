package ru.sepparalex.accomodrental.sevices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.Client;
import ru.sepparalex.accomodrental.repositories.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    public List<Client> findAll(){
        return clientRepository.findAll();
    }
}
