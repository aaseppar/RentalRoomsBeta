package ru.sepparalex.accomodrental.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.Client;
import ru.sepparalex.accomodrental.repositories.ClientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Autowired
    public UserDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("Client does not exist!"));
        return SecurityUser.fromUser(client);
    }
    //Authentication-информация о текущем клиенте
    public boolean hasUserId(Authentication authentication, int userId) {
        Client client = clientRepository.findByEmail(authentication.getName()).orElseThrow();
        return client.getId() == userId;
    }
    public boolean hasNoUserId(Authentication authentication, int userId) {
        Client client = clientRepository.findByEmail(authentication.getName()).orElseThrow();
        return client.getId() != userId;
    }
}