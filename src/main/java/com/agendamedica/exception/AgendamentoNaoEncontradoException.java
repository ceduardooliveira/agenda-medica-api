package com.agendamedica.exception;

import java.util.UUID;

public class AgendamentoNaoEncontradoException extends RuntimeException {
    public AgendamentoNaoEncontradoException(UUID id) {
        super("Agendamento não encontrado com ID: " + id);
    }
}
