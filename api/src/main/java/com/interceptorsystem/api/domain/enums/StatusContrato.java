package com.interceptorsystem.api.domain.enums;

public enum StatusContrato {
    PAGO,
    PENDENTE,
    INATIVO;

    public boolean isBlank() {
        return false;
    }
}
