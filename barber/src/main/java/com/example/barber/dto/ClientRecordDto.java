package com.example.barber.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientRecordDto(@NotBlank String name, @NotBlank String telefone) {
}
