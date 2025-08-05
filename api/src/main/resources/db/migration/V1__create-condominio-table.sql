CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Criação da tabela de Condomínios
CREATE TABLE condominio (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj_numero VARCHAR(18) UNIQUE,
    endereco_logradouro VARCHAR(255),
    endereco_numero VARCHAR(20),
    endereco_cidade VARCHAR(100),
    endereco_estado VARCHAR(50),
    endereco_cep VARCHAR(10),
    status VARCHAR(50) NOT NULL
);