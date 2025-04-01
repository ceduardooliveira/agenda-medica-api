package com.agendamedica.service.impl;

import com.agendamedica.dto.request.AgendamentoRequest;
import com.agendamedica.dto.response.AgendamentoResponse;
import com.agendamedica.exception.AgendamentoConflitanteException;
import com.agendamedica.exception.AgendamentoNaoEncontradoException;
import com.agendamedica.exception.PacienteNaoEncontradoException;
import com.agendamedica.model.Agendamento;
import com.agendamedica.model.Paciente;
import com.agendamedica.repository.AgendamentoRepository;
import com.agendamedica.repository.PacienteRepository;
import com.agendamedica.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final PacienteRepository pacienteRepository;

    @Override
    @Transactional
    public AgendamentoResponse salvar(AgendamentoRequest request) {
        Paciente paciente = pacienteRepository.findById(request.pacienteId())
                .orElseThrow(() -> new PacienteNaoEncontradoException(request.pacienteId()));

        if (agendamentoRepository.existsByDataHoraAndPacienteId(
                request.dataHora(),
                request.pacienteId())) {
            throw new AgendamentoConflitanteException(request.dataHora());
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setPaciente(paciente);
        agendamento.setDataHora(request.dataHora());
        agendamento.setMotivo(request.motivo());
        agendamento.setObservacoes(request.observacoes());

        Agendamento salvo = agendamentoRepository.save(agendamento);
        return toResponse(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public AgendamentoResponse buscarPorId(UUID id) {
        return agendamentoRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgendamentoResponse> buscarTodos() {
        return agendamentoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgendamentoResponse> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return agendamentoRepository.findByDataHoraBetween(inicio, fim).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AgendamentoResponse atualizar(UUID id, AgendamentoRequest request) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(id));

        Paciente paciente = pacienteRepository.findById(request.pacienteId())
                .orElseThrow(() -> new PacienteNaoEncontradoException(request.pacienteId()));

        agendamento.setPaciente(paciente);
        agendamento.setDataHora(request.dataHora());
        agendamento.setMotivo(request.motivo());
        agendamento.setObservacoes(request.observacoes());

        Agendamento atualizado = agendamentoRepository.save(agendamento);
        return toResponse(atualizado);
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new AgendamentoNaoEncontradoException(id);
        }
        agendamentoRepository.deleteById(id);
    }

    private AgendamentoResponse toResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getPaciente().getId(),
                agendamento.getPaciente().getNome(),
                agendamento.getDataHora(),
                agendamento.getMotivo(),
                agendamento.getObservacoes()
        );
    }
}