CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE alocacao (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    data DATE NOT NULL,
    status_alocacao VARCHAR(50) NOT NULL,
    tipo_alocacao VARCHAR(50) NOT NULL,
    posto_de_trabalho_id UUID NOT NULL,
    funcionario_id UUID NOT NULL,
    CONSTRAINT fk_alocacao_posto FOREIGN KEY (posto_de_trabalho_id) REFERENCES posto_de_trabalho(id),
    CONSTRAINT fk_alocacao_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);