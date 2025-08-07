CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE funcionario (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf_numero VARCHAR(14) UNIQUE,
    -- Adicionando os campos de endereço para o funcionário
    endereco_logradouro VARCHAR(255),
    endereco_numero VARCHAR(20),
    endereco_cidade VARCHAR(100),
    endereco_estado VARCHAR(50),
    endereco_cep VARCHAR(10),
    -- Fim dos campos de endereço
    status_funcionario VARCHAR(50) NOT NULL,
    tipo_escala VARCHAR(50) NOT NULL,
    tipo_funcionario VARCHAR(50) NOT NULL,
    salario_mensal DECIMAL(10, 2),
    valor_total_beneficios_mensal DECIMAL(10, 2),
    valor_diaria_fixa DECIMAL(10, 2)
);