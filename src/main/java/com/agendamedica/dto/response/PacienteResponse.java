package com.agendamedica.dto.response;

import java.util.UUID;

public record PacienteResponse(
        UUID id,
        String nome,
        String cpf,
        String telefone,
        String email
) {}