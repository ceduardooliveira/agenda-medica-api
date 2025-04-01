package com.agendamedica.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoResponse(
        UUID id,
        UUID pacienteId,
        String pacienteNome,
        LocalDateTime dataHora,
        String motivo,
        String observacoes
) {}