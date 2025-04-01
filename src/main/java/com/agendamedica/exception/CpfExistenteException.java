package com.agendamedica.exception;

public class CpfExistenteException extends RuntimeException {
    public CpfExistenteException(String cpf) {
        super("Já existe um paciente cadastrado com o CPF: " + cpf);
    }
}