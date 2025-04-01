package com.agendamedica.service;

import com.agendamedica.dto.request.PacienteRequest;
import com.agendamedica.dto.response.PacienteResponse;
import java.util.List;
import java.util.UUID;

public interface PacienteService {
    PacienteResponse salvar(PacienteRequest request);
    PacienteResponse buscarPorId(UUID id);
    List<PacienteResponse> buscarTodos();
    PacienteResponse atualizar(UUID id, PacienteRequest request);
    void deletar(UUID id);
}
