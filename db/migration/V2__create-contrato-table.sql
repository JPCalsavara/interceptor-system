CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE contrato (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    valor_diaria_cobrada DECIMAL(10, 2) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    condominio_id UUID NOT NULL,
    CONSTRAINT fk_contrato_condominio FOREIGN KEY (condominio_id) REFERENCES condominios(id)
);