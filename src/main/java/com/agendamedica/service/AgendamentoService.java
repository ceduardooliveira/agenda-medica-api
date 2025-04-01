package com.agendamedica.service;

import com.agendamedica.dto.request.AgendamentoRequest;
import com.agendamedica.dto.response.AgendamentoResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AgendamentoService {
    AgendamentoResponse salvar(AgendamentoRequest request);
    AgendamentoResponse buscarPorId(UUID id);
    List<AgendamentoResponse> buscarTodos();
    List<AgendamentoResponse> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
    AgendamentoResponse atualizar(UUID id, AgendamentoRequest request);
    void deletar(UUID id);
}
