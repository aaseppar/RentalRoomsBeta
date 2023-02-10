package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sepparalex.accomodrental.error.*;
import ru.sepparalex.accomodrental.models.*;
import ru.sepparalex.accomodrental.repositories.ClientRepository;

import javax.management.BadAttributeValueExpException;
import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final CityService cityService;
    private final CountryService countryService;
    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findByLoginAndEmail(String login, String email){
      Client client= clientRepository.findByLoginAndEmail(login,email);
      if(client==null)   throw new NoClientByLoginAndEmailException("There isn't Client with that Login and Email");
      else return client;
    }

    @Transactional
    public Client save(Client client,int flagChange){
       if(flagChange==0)
       {
         AtomicInteger flagExistEmail= new AtomicInteger(0);
         List<Client> clients = clientRepository.findAll();
        clients.forEach(c->{if(c.getEmail().equals(client.getEmail())){
            flagExistEmail.set(1);}
        });
           System.out.println(flagExistEmail.get());
        if(flagExistEmail.get()==1) {
           throw new NoClientSaveByEmailException("The Client with that email already exist");
          }

           boolean matchesEmail=client.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
           if(matchesEmail==false){
               throw new NoClientSaveByValidateEmailException("Failure check email validation");
           }

           BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           String encodedPassword = passwordEncoder.encode(client.getPassword());
           client.setPassword(encodedPassword);

        }
      if(flagChange==3){
          AtomicInteger flagExistEmail= new AtomicInteger(0);
          List<Client> clients = clientRepository.findAll();
          clients.forEach(c->{if(c.getEmail().equals(client.getEmail())&&(c.getId()!=client.getId())){
              flagExistEmail.set(1);
          }
          });

          if(flagExistEmail.get()==1) {
              throw new NoClientSaveByEmailException("The Client with that email already exist");
          }

          boolean matchesEmail=client.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
          if(matchesEmail==false){
              throw new NoClientSaveByValidateEmailException("Failure check email validation");
          }

      }
     if(flagChange==2) {
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         String encodedPassword = passwordEncoder.encode(client.getPassword());
         client.setPassword(encodedPassword);

     }

     AtomicInteger flagEqualsCityIDAndName= new AtomicInteger();
     List<City> cities = cityService.findAll();
     cities.forEach(c->{if((c.getId()==client.getCity().getId())&&(c.getName().equals(client.getCity().getName()))){
         flagEqualsCityIDAndName.set(1);}
     });

       if(flagEqualsCityIDAndName.get()!=1){
          for(int i=0;i<cities.size();i++){
              City city=cities.get(i);
          if((city.getName().equals(client.getCity().getName()))&&(city.getId()!=client.getCity().getId())) {//client insert wrong city id
           client.getCity().setId(city.getId());// insert true city id to client
           break;
           }
          else{
              if(((!(city.getName().equals(client.getCity().getName())))&&(city.getId()==client.getCity().getId()))||
                (!(city.getName().equals(client.getCity().getName())))&&(city.getId()!=client.getCity().getId())) {
                  //Need make new City!!!!!!!!!!!!!!!
                  City city1= new City(client.getCity().getName(),client.getCity().getCountry());
                  client.setCity(city1);
                  cityService.save(city1);
                  break;
              }
          }
        }
       }

        AtomicInteger flagEqualsCountryIDAndName= new AtomicInteger();
        List<Country> countryList = countryService.findAll();
        countryList.forEach(c->{if((c.getId()==client.getCity().getCountry().getId()) //Equals countryId and countryName
                                   &&(c.getName().equals(client.getCity().getCountry().getName()))){
            flagEqualsCountryIDAndName.set(1);}
        });
        if(flagEqualsCountryIDAndName.get()!=1){ // Not Equals countryId or countryName
            for(int i=0;i<countryList.size();i++){
                Country country=countryList.get(i);
                if((country.getName().equals(client.getCity().getCountry().getName()))
                        &&(country.getId()!=client.getCity().getCountry().getId())) { //Equal name country bat not equal id country
                    client.getCity().getCountry().setId(country.getId());
                    break;
                }
                else{
                    if(((!(country.getName().equals(client.getCity().getCountry().getName())))&&(country.getId()==client.getCity().getCountry().getId()))||
                      (!(country.getName().equals(client.getCity().getCountry().getName())))&&(country.getId()!=client.getCity().getCountry().getId())) {
                        //Need make new Country!!!!!!!!!!!!!!!
                        Country country1= new Country(client.getCity().getCountry().getName());
                        countryService.save(country1);
                        break;
                    }
                }
            }
        }
        return clientRepository.save(client);
     }

    public Client setClientStatus(Client client, Status status) {
        if(status.equals(Status.BANNED)) {// will to banned
            if((!(client.getRole().equals(Role.ADMIN)))&&(!(client.getRole().equals(Role.SUPERADMIN)))
                    &&(client.getRating().getValue()<=1)){
                client.setStatus(status);
            }
            else {
                throw new NoClientSaveByStatusADMIN("This client is not allowed to change status because it is ADMIN or SUPERADMIN or have rating more 1");
            }

        }
        else if(status.equals(Status.ACTIVE)){ // will to activated
            if (client.getRating().getValue() >= 2)  {
                client.setStatus(status);
            }
            else{
                throw new NoClientSaveByStatusHighRating("This client is not allowed to change status because it have rating less 2");
            }
        }
        return client;
    }
}


