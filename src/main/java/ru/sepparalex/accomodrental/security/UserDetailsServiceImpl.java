package ru.sepparalex.accomodrental.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.error.NoClientSaveByEmailException;
import ru.sepparalex.accomodrental.error.hasNoUserIdByEmailException;
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
    public boolean hasUserId(Authentication authentication, String loginClient, String emailClient) {
        Client client =clientRepository.findByLoginAndEmail(loginClient,emailClient);
        String email=authentication.getName();
        return client.getEmail().equals(email);
    }

    public boolean hasNoUserId(Authentication authentication,String loginClient, String emailClient) {
        Client client=clientRepository.findByLoginAndEmail(loginClient,emailClient);
        if(client==null){
            throw new hasNoUserIdByEmailException("Client not found with this email");
        }
        String email=authentication.getName();
        return !(emailClient.equals(email));
    }
}