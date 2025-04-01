package com.agendamedica.service.impl;

import com.agendamedica.dto.request.PacienteRequest;
import com.agendamedica.dto.response.PacienteResponse;
import com.agendamedica.exception.CpfExistenteException;
import com.agendamedica.exception.PacienteNaoEncontradoException;
import com.agendamedica.model.Paciente;
import com.agendamedica.repository.PacienteRepository;
import com.agendamedica.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository repository;

    @Override
    @Transactional
    public PacienteResponse salvar(PacienteRequest request) {
        if (repository.existsByCpf(request.cpf())) {
            throw new CpfExistenteException(request.cpf());
        }

        Paciente paciente = new Paciente();
        paciente.setNome(request.nome());
        paciente.setCpf(request.cpf());
        paciente.setTelefone(request.telefone());
        paciente.setEmail(request.email());

        Paciente salvo = repository.save(paciente);
        return toResponse(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public PacienteResponse buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new PacienteNaoEncontradoException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PacienteResponse> buscarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PacienteResponse atualizar(UUID id, PacienteRequest request) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException(id));

        paciente.setNome(request.nome());
        paciente.setTelefone(request.telefone());
        paciente.setEmail(request.email());

        Paciente atualizado = repository.save(paciente);
        return toResponse(atualizado);
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new PacienteNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }

    private PacienteResponse toResponse(Paciente paciente) {
        return new PacienteResponse(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail()
        );
    }
}
