package com.example.barber.model.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientProducer {

    @JsonProperty("Nome")
    private String name;

    @JsonProperty("Celulares")
    private String telefone;

}
