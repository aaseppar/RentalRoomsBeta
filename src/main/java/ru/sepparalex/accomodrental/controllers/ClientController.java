package ru.sepparalex.accomodrental.controllers;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.sepparalex.accomodrental.models.Client;
import ru.sepparalex.accomodrental.models.Status;
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
    @PostMapping
    @PreAuthorize("hasAnyAuthority('client:write')")
        public Client createClient(@RequestBody Client client){
        return clientService.save(client);

    }
    @PatchMapping("/{id}/{email}")
    @PreAuthorize("hasAnyAuthority('client:write')")
    public Client patchClient(@PathVariable("id") int id,@PathVariable("email") String email) {
       Client client=clientService.findById(id);
       client.setEmail(email);
        clientService.save(client);
        return client;
    }
    @PatchMapping("/{id}/")
    @PreAuthorize("hasAnyAuthority('client:write')")
    public Client patchClient(@PathVariable("id") int id,@RequestParam(name="status",required = true) Status status) {
        Client client=clientService.findById(id);
        client.setStatus(status);
        clientService.save(client);
        return client;
    }
}