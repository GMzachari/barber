package com.example.barber.service;

import com.example.barber.controller.ClientController;
import com.example.barber.model.ClientModel;
import com.example.barber.model.producer.ClientProducer;
import com.example.barber.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<ClientModel> getClients(){


        List<ClientModel> clientList = clientRepository.findAll();
        if(!clientList.isEmpty()){

            for(ClientModel clients : clientList){
                UUID id = clients.getIdClient();
                clients.add(linkTo(methodOn(ClientController.class).getClientById(id)).withSelfRel());
            }
        }

        return clientList;
    }

    public List<ClientProducer> getClientByName(String name){

        List<ClientProducer> clientes = new ArrayList<>();

        var clientsBd = clientRepository.findAll();

        for(ClientModel cl : clientsBd){
            clientes.add(new ClientProducer(
                    cl.getName(),
                    cl.getTelefone()
            ));
        }

        var clienteFiltrado = clientes.stream()
                .filter(f -> f.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        return clienteFiltrado;
    }

    public Optional<ClientModel> getClientById(UUID id){

       Optional<ClientModel> clientesFiltrados;
       clientesFiltrados = clientRepository.findById(id);

       return clientesFiltrados;
    }

}
