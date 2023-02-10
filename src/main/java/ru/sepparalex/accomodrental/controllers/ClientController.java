package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.error.*;
import ru.sepparalex.accomodrental.models.*;
import ru.sepparalex.accomodrental.services.CityService;
import ru.sepparalex.accomodrental.services.ClientService;
import ru.sepparalex.accomodrental.services.CountryService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Slf4j
public class ClientController {
    private final ClientService clientService;
    private final CountryService countryService;
    private final CityService cityService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('client:read')")
    public List<Client>  getAll(){
        List<Client> client=clientService.findAll();
        return client;
    }
    @PostMapping()
        public Client createClient(@RequestBody Client client){
        client.setRole(Role.USER);
        client.setStatus(Status.ACTIVE);
        return clientService.save(client,0);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleNoSaveClientByEmail(NoClientSaveByEmailException exc){// That email already exist
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("{login}/{email}/{newEmail}")    // Change email
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #login, #email) and hasAuthority('client:write')")
    public Client patchClientEmail(@PathVariable("login") String login, @PathVariable("email") String email,
                                   @PathVariable("newEmail") String newEmail) {
       Client client=clientService.findByLoginAndEmail(login,email);
       client.setEmail(newEmail);
       return clientService.save(client,3);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoClientByLoginAndPasswd(NoClientByLoginAndEmailException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage NoClientSaveByValidateEmail(NoClientSaveByValidateEmailException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{login}/{email}/role/") //Set role
    @PreAuthorize("hasAnyAuthority('client:set_role')")
    public Client patchClientRole(@PathVariable("login") String login, @PathVariable("email") String email,
                                  @RequestParam(name="role",required = true) Role role) {
        Client client=clientService.findByLoginAndEmail(login,email);
        client.setRole(role);
        return clientService.save(client,4);

    }
    @PatchMapping("/{login}/{email}/status/") //Set status
    @PreAuthorize("hasAnyAuthority('client:set_status')")
    public Client patchClientStatus(@PathVariable("login") String login, @PathVariable("email") String email,
                                    @RequestParam(name="status",required = true) Status status) {
        Client client=clientService.findByLoginAndEmail(login,email);
        Client client1=clientService.setClientStatus(client,status);

      return clientService.save(client1,5);

    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage NotSaveClientByStatus(NoClientSaveByStatusADMIN exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage NotSaveClientByStatusHighRating(NoClientSaveByStatusHighRating exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{login}/{email}/rating/") //Set rating
    @PreAuthorize("@userDetailsServiceImpl.hasNoUserId(authentication,#login,#email) and hasAuthority('client:write')")
    public Client patchClientRating(@PathVariable("login") String login, @PathVariable("email") String email,
                                    @RequestParam(name="rating",required = true) Rating rating) {
        Client client=clientService.findByLoginAndEmail(login,email);
        client.setRating(rating);
        return clientService.save(client,7);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage hasNoUserIdByEmailException(hasNoUserIdByEmailException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{login}/{email}/city/") //change City
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #login, #email) or hasAuthority('client:write')")
    public Client createClient(@PathVariable("login") String login,@PathVariable("email") String email,
                               @RequestParam(name="cityName",required = true) String cityName,
                               @RequestParam(name="countryName",required = true) String countryName) {
        Client client=clientService.findByLoginAndEmail(login,email);
        Country country=new Country(countryName);
        countryService.save(country);
        City city=new City(cityName,country);
        cityService.save(city);
        client.setCity(city);
        return clientService.save(client,6);
     }
    @PatchMapping("/{login}/{email}/newLogin/{newLogin}") // change login
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #login,#email) and hasAuthority('client:write')")
       public Client patchClientLogin(@PathVariable("login") String login, @PathVariable("email") String email,
                                      @PathVariable("newLogin") String newLogin) {
        Client client=clientService.findByLoginAndEmail(login,email);
        client.setLogin(newLogin);
       return clientService.save(client,1);
    }
    @PatchMapping("/{login}/{email}/newPasswd/{password}") // change password
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #login, #email) and hasAuthority('client:write')")
     public Client patchClientPassword(@PathVariable("login") String login,@PathVariable("email") String email,
                                       @PathVariable("password") String password) {
        Client client=clientService.findByLoginAndEmail(login,email);
        client.setPassword(password);
        return clientService.save(client,2);
    }
}