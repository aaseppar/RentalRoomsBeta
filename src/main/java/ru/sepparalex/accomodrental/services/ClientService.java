package ru.sepparalex.accomodrental.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sepparalex.accomodrental.models.City;
import ru.sepparalex.accomodrental.models.Client;
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
          cities.forEach(c->{
          if((c.getName().equals(client.getCity().getName()))&&(c.getId()!=client.getCity().getId())) {
           client.getCity().setId(c.getId());
           }
          else{
              if(((!(c.getName().equals(client.getCity().getName())))&&(c.getId()==client.getCity().getId()))||
                      (!(c.getName().equals(client.getCity().getName())))&&(c.getId()!=client.getCity().getId())) {
                  int lastId = cities.get(cities.size() - 1).getId()+1;
                  //Need make new City!!!!!!!!!!!!!!!
                  City city= new City(lastId,client.getCity().getName(),client.getCity().getCountry());
                  client.setCity(city);
                  cityService.save(city);
              }
          }
        });
        }
        return clientRepository.save(client);
     }
  }


