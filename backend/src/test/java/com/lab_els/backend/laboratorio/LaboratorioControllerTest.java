package com.lab_els.backend.laboratorio;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LaboratorioControllerTest {

    private static final String VALIDO =
            "{\"nome\":\"Lab Química\",\"localizacao\":\"Bloco A\",\"capacidade\":20,\"descricao\":\"Aulas práticas\"}";

    @Autowired
    MockMvc mvc;

    @Autowired
    LaboratorioRepository repository;

    @BeforeEach
    void limpar() {
        repository.deleteAll();
    }

    @Test
    void crudCompleto() throws Exception {
        mvc.perform(post("/api/laboratorios").contentType(MediaType.APPLICATION_JSON).content(VALIDO))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Lab Química"));

        Long id = repository.findAll().get(0).getId();

        mvc.perform(get("/api/laboratorios")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
        mvc.perform(get("/api/laboratorios/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.capacidade").value(20));

        mvc.perform(put("/api/laboratorios/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Lab Física\",\"capacidade\":30}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Lab Física"))
                .andExpect(jsonPath("$.capacidade").value(30));

        mvc.perform(delete("/api/laboratorios/{id}", id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/laboratorios/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    void rejeitaDadosInvalidos() throws Exception {
        mvc.perform(post("/api/laboratorios").contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"\",\"capacidade\":0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erros.nome").exists())
                .andExpect(jsonPath("$.erros.capacidade").exists());
    }

    @Test
    void atualizarInexistenteRetorna404() throws Exception {
        mvc.perform(put("/api/laboratorios/{id}", 9999).contentType(MediaType.APPLICATION_JSON).content(VALIDO))
                .andExpect(status().isNotFound());
    }
}
