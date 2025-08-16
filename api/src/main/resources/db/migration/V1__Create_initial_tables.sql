-- Cria a tabela para Condominios (sem dependências)
CREATE TABLE condominios (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    status VARCHAR(255),
    cnpj VARCHAR(255) UNIQUE NOT NULL,
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    cep VARCHAR(255)
);

-- Cria a tabela para Funcionarios (sem dependências)
CREATE TABLE funcionarios (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    status_funcionario VARCHAR(255) NOT NULL,
    tipo_escala VARCHAR(255) NOT NULL,
    tipo_funcionario VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) UNIQUE NOT NULL,
    celular VARCHAR(255) NOT NULL,
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    cep VARCHAR(255)
);

-- Cria a tabela para Contratos (depende de condominios)
CREATE TABLE contratos (
    id UUID PRIMARY KEY,
    descricao VARCHAR(255),
    valor_total NUMERIC(19, 2),
    valor_diaria_cobrada NUMERIC(19, 2),
    data_inicio DATE,
    data_fim DATE,
    status VARCHAR(255),
    condominio_id UUID NOT NULL,
    FOREIGN KEY (condominio_id) REFERENCES condominios(id)
);

-- Cria a tabela para Postos de Trabalho (depende de condominios)
CREATE TABLE postos_de_trabalho (
    id UUID PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    condominio_id UUID NOT NULL,
    FOREIGN KEY (condominio_id) REFERENCES condominios(id)
);

-- Cria a tabela para Alocacoes (depende de funcionarios e postos_de_trabalho)
CREATE TABLE alocacoes (
    id UUID PRIMARY KEY,
    data_alocacao DATE NOT NULL,
    funcionario_id UUID NOT NULL,
    posto_de_trabalho_id UUID NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
    FOREIGN KEY (posto_de_trabalho_id) REFERENCES postos_de_trabalho(id),
    UNIQUE (funcionario_id, posto_de_trabalho_id)
);
