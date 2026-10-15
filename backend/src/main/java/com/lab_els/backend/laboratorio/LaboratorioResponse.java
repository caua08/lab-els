package com.lab_els.backend.laboratorio;

public record LaboratorioResponse(
        Long id,
        String nome,
        String localizacao,
        Integer capacidade,
        String descricao) {

    static LaboratorioResponse from(Laboratorio lab) {
        return new LaboratorioResponse(
                lab.getId(), lab.getNome(), lab.getLocalizacao(), lab.getCapacidade(), lab.getDescricao());
    }
}
