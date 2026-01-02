package com.libri.catalog.domain.enums;

public enum RegistrationStatus {
    SUCCESS("Livro registrado com sucesso"),
    MISSING_DATA("Dados obrigatórios faltando"),
    VALIDATION_ERROR("Erro de validação nos dados"),
    DATABASE_ERROR("Erro ao salvar no banco de dados"),
    UNKNOWN_ERROR("Erro desconhecido OU Erro interno");

    private final String defaultMessage;

    RegistrationStatus(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
