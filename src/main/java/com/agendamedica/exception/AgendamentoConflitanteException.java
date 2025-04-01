package com.agendamedica.exception;

import java.time.LocalDateTime;

public class AgendamentoConflitanteException extends RuntimeException {
    public AgendamentoConflitanteException(LocalDateTime dataHora) {
        super("Já existe um agendamento para o paciente nesta data/hora: " + dataHora);
    }
}