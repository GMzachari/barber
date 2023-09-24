package com.example.barber.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENTS")
public class ClientModel extends RepresentationModel<ClientModel> implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    private String name;
    private String telefone;

    public UUID getIdClient() {
        return idClient;
    }

    public void setIdClient(UUID idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
