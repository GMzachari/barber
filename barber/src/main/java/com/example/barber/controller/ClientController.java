package com.example.barber.controller;

import com.example.barber.dto.ClientRecordDto;
import com.example.barber.model.ClientModel;
import com.example.barber.model.producer.ClientProducer;
import com.example.barber.repository.ClientRepository;
import com.example.barber.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@Controller
@RequestMapping(value = "/barber")
@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @PostMapping("/save_clients")
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientRecordDto clientRecordDto){

        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(clientModel));
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getClients(){

        var response = clientService.getClients();
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/clients_name-{name}")
    public ResponseEntity<?> getClientsByName(@PathVariable String name){

        var response = clientService.getClientByName(name);
        if(response != null){
             return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/client_id-{id}")
    public ResponseEntity<?> getClientById(@PathVariable UUID id){

        var response = clientService.getClientById(id);
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id,
                                           @RequestBody @Valid ClientRecordDto clientRecordDto){
        Optional<ClientModel> client = clientRepository.findById(id);

        if(client.isEmpty()){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var clientModel = client.get();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable UUID id){

        Optional<ClientModel> client = clientRepository.findById(id);
        clientRepository.delete(client.get());
        if(client.isEmpty()){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
