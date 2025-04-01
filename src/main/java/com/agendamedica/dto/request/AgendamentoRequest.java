package com.agendamedica.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoRequest(
        @NotNull UUID pacienteId,
        @NotNull @Future LocalDateTime dataHora,
        @NotBlank String motivo,
        String observacoes
) {}