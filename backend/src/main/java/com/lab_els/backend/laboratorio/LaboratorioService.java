package com.lab_els.backend.laboratorio;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lab_els.backend.exception.RecursoNaoEncontradoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LaboratorioService {

    private final LaboratorioRepository repository;

    @Transactional(readOnly = true)
    public List<LaboratorioResponse> listar() {
        return repository.findAll().stream().map(LaboratorioResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public LaboratorioResponse buscar(Long id) {
        return LaboratorioResponse.from(obter(id));
    }

    public LaboratorioResponse criar(LaboratorioRequest request) {
        Laboratorio lab = new Laboratorio();
        aplicar(lab, request);
        return LaboratorioResponse.from(repository.save(lab));
    }

    public LaboratorioResponse atualizar(Long id, LaboratorioRequest request) {
        Laboratorio lab = obter(id);
        aplicar(lab, request);
        return LaboratorioResponse.from(repository.save(lab));
    }

    public void remover(Long id) {
        repository.delete(obter(id));
    }

    private Laboratorio obter(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Laboratório " + id + " não encontrado"));
    }

    private void aplicar(Laboratorio lab, LaboratorioRequest request) {
        lab.setNome(request.nome());
        lab.setLocalizacao(request.localizacao());
        lab.setCapacidade(request.capacidade());
        lab.setDescricao(request.descricao());
    }
}
