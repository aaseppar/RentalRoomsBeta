package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.City;
import ru.sepparalex.accomodrental.models.Client;
import ru.sepparalex.accomodrental.models.Country;
import ru.sepparalex.accomodrental.models.Role;
import ru.sepparalex.accomodrental.repositories.ClientRepository;

import javax.transaction.Transactional;
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

    public Client findById(int id){
        Optional<Client> res=clientRepository.findById(id);
        return res.orElseThrow();
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
            System.out.println(String.format("This email=%s already exist! Input another email.",client.getEmail()));
        return null;
        }
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

    public Client findByFullName(String clientFullName) {
        return clientRepository.findByUserfullnameInoreCase(clientFullName);
    }

    public Client findByCityId(int citiIdforTake) {
        return clientRepository.findByCityId(citiIdforTake);
    }
}


