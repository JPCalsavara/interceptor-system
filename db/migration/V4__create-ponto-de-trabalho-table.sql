CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE posto_de_trabalho (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    condominio_id UUID NOT NULL,
    CONSTRAINT fk_posto_condominio FOREIGN KEY (condominio_id) REFERENCES condominios(id)
);