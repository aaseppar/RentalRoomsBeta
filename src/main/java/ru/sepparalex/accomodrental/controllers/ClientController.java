package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.models.*;
import ru.sepparalex.accomodrental.services.ClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('client:read')")
    public List<Client>  getAll(){
        List<Client> client=clientService.findAll();
        //client.forEach(c -> System.out.println(c));
        return client;
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('client:read')")
    public Client getById(@PathVariable("id") int id) {
        return clientService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('client:create')")
        public Client createClient(@RequestBody Client client){
        return clientService.save(client,0);

    }
    @PatchMapping("/{id}/{email}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('client:write')")
    public Client patchClientEmail(@PathVariable("id") int id,@PathVariable("email") String email) {
       Client client=clientService.findById(id);
       client.setEmail(email);
        return clientService.save(client,3);

    }
    @PatchMapping("/{id}/{clientId}/role/")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('client:set_role')")
    public Client patchClientRole(@PathVariable("clientId") int clientId,@RequestParam(name="role",required = true) Role role) {
        Client client=clientService.findById(clientId);
        client.setRole(role);
        return clientService.save(client,4);

    }
    @PatchMapping("/{id}/{clientId}/status/")
    @PreAuthorize("@userDetailsServiceImpl.hasNoUserId(authentication, #id) and hasAuthority('client:write')")
    public Client patchClientStatus(@PathVariable("clientId") int clientId,@RequestParam(name="status",required = true) Status status) {
        Client client=clientService.findById(clientId);
        client.setStatus(status);
       return clientService.save(client,5);

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