package com.agendamedica.exception;

import java.util.UUID;

public class PacienteNaoEncontradoException extends RuntimeException {
    public PacienteNaoEncontradoException(UUID id) {
        super("Paciente não encontrado com ID: " + id);
    }
}
