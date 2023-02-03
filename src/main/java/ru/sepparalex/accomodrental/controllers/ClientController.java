package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.models.*;
import ru.sepparalex.accomodrental.services.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
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

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('client:write')")
        public Client createClient(@RequestBody Client client){
        client.setRole(Role.USER);
        return clientService.save(client,0);

    }
    @PatchMapping("/{id}/{email}")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) and hasAuthority('client:write')")
    public Client patchClientEmail(@PathVariable("id") int id,@PathVariable("email") String email) {
       Client client=clientService.findById(id);
       client.setEmail(email);
        return clientService.save(client,3);

    }
    @PatchMapping("/{id}/")
    @PreAuthorize("@userDetailsServiceImpl.hasUserId(authentication, #id) or hasAuthority('client:set_role')")
    public Client patchClientRole(@PathVariable("id") int id,@RequestParam(name="clientId",required = true) int clientId,@RequestParam(name="role",required = true) Role role) {
        Client client=clientService.findById(clientId);
        client.setRole(role);
        return clientService.save(client,4);

    }
    @PatchMapping("/{id}/{clientFullName}/status/")
    @PreAuthorize("@userDetailsServiceImpl.hasNoUserId(authentication, #id) and hasAuthority('client:write')")
    public Client patchClientStatus(@PathVariable("id") int id,
                                    @PathVariable("clientFullName") String clientFullName,
                                    @RequestParam(name="status",required = true) Status status) {
        Client client=clientService.findByFullName(clientFullName);
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