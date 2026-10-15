package com.lab_els.backend.laboratorio;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/laboratorios")
@RequiredArgsConstructor
public class LaboratorioController {

    private final LaboratorioService service;

    @GetMapping
    public List<LaboratorioResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public LaboratorioResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<LaboratorioResponse> criar(@Valid @RequestBody LaboratorioRequest request) {
        LaboratorioResponse criado = service.criar(request);
        return ResponseEntity.created(URI.create("/api/laboratorios/" + criado.id())).body(criado);
    }

    @PutMapping("/{id}")
    public LaboratorioResponse atualizar(@PathVariable Long id, @Valid @RequestBody LaboratorioRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
