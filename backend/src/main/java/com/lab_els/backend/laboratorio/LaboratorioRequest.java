package com.lab_els.backend.laboratorio;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LaboratorioRequest(
        @NotBlank @Size(max = 120) String nome,
        @Size(max = 120) String localizacao,
        @NotNull @Min(1) Integer capacidade,
        @Size(max = 500) String descricao) {
}
