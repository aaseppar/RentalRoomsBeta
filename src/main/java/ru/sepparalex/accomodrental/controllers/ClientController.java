package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.error.*;
import ru.sepparalex.accomodrental.models.*;
import ru.sepparalex.accomodrental.services.ClientService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Slf4j
public class ClientController {
    private final ClientService clientService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('client:read')")
    public List<Client>  getAll(){
        List<Client> client=clientService.findAll();
        return client;
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('client:read')")
    public Client getById(@PathVariable("id") int id) {
        return clientService.findById(id);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoClientById(NoClientByIdException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.NOT_FOUND);
    }
    @PostMapping()
        public Client createClient(@RequestBody Client client){
        client.setRole(Role.USER);
        client.setStatus(Status.ACTIVE);
        return clientService.save(client,0);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleNoSaveClientByEmail(NoClientSaveByEmailException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{id}/{email}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and  hasAuthority('client:write')")
    public Client patchClientEmail(@PathVariable("id") int id,@PathVariable("email") String email) {
       Client client=clientService.findById(id);
       client.setEmail(email);
        return clientService.save(client,3);
    }
    @PatchMapping("/{clientId}/role/")
    @PreAuthorize("hasAnyAuthority('client:set_role')")
    public Client patchClientRole(@PathVariable("clientId") int clientId,@RequestParam(name="role",required = true) Role role) {
        Client client=clientService.findById(clientId);
        client.setRole(role);
        return clientService.save(client,4);

    }
    @PatchMapping("/{clientFullName}/status/")
    @PreAuthorize("hasAnyAuthority('client:set_status')")
    public Client patchClientStatus(@PathVariable("clientFullName") String clientFullName,
                                    @RequestParam(name="status",required = true) Status status) {
        Client client=clientService.findByFullName(clientFullName);

        if(status.equals(Status.BANNED)) {// will to banned
           if((!(client.getRole().equals(Role.ADMIN)))&&(!(client.getRole().equals(Role.SUPERADMIN)))
                &&(client.getRating().getValue()<=1)){
                client.setStatus(status);
           }
        }
        else if(status.equals(Status.ACTIVE)){ // will to activated
             if (client.getRating().getValue() >= 2)  {
                client.setStatus(status);
            }
        }
      return clientService.save(client,5);

    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoClientByFullName(NoClientByFullNameException exc){
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(),HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}/rating/")
    @PreAuthorize("@userDetailsServiceImpl.hasNoUserId(authentication, #id) and hasAuthority('client:write')")
    public Client patchClientRating(@PathVariable("id") int id,@RequestParam(name="rating",required = true) Rating rating) {
        Client client=clientService.findById(id);
        client.setRating(rating);
        return clientService.save(client,7);
    }
    @PatchMapping("/{id}/city/")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('client:write')")
    public Client createClient(@PathVariable("id") int id,@RequestParam(name="city",required = true) City city) {
        Client client=clientService.findById(id);
        client.setCity(city);
        return clientService.save(client,6);
     }
    @PatchMapping("/{id}/login/{login}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('client:write')")
       public Client patchClientLogin(@PathVariable("id") int id,@PathVariable("login") String login) {
        Client client=clientService.findById(id);
        client.setLogin(login);
       return clientService.save(client,1);
    }
    @PatchMapping("/{id}/passwd/{password}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('client:write')")
   // @PreAuthorize("hasAnyAuthority('client:write')")
    public Client patchClientPassword(@PathVariable("id") int id,@PathVariable("password") String password) {
        Client client=clientService.findById(id);
        client.setPassword(password);
        return clientService.save(client,2);

    }
}