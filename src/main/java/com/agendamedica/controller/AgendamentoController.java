package com.agendamedica.controller;

import com.agendamedica.dto.request.AgendamentoRequest;
import com.agendamedica.dto.response.AgendamentoResponse;
import com.agendamedica.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {
    private final AgendamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoResponse criar(@RequestBody @Valid AgendamentoRequest request) {
        return service.salvar(request);
    }

    @GetMapping
    public List<AgendamentoResponse> listarTodos() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    public AgendamentoResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/periodo")
    public List<AgendamentoResponse> buscarPorPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        return service.buscarPorPeriodo(inicio, fim);
    }

    @PutMapping("/{id}")
    public AgendamentoResponse atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AgendamentoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
