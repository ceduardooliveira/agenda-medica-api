package com.agendamedica.controller;

import com.agendamedica.dto.request.PacienteRequest;
import com.agendamedica.dto.response.PacienteResponse;
import com.agendamedica.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponse criar(@RequestBody @Valid PacienteRequest request) {
        return service.salvar(request);
    }

    @GetMapping
    public List<PacienteResponse> listarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    public PacienteResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PacienteResponse atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid PacienteRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}