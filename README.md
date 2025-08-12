# 🛡️ Sistema de Gestão Inteligente para Segurança e Terceirização - Interceptor System

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](#)
[![Version](https://img.shields.io/badge/version-1.5-blue)](#)
[![License](https://img.shields.io/badge/license-MIT-lightgrey)](#)
[![Java](https://img.shields.io/badge/java-21-red)](#)
[![Spring Boot](https://img.shields.io/badge/spring%20boot-3-green)](#)
[![React](https://img.shields.io/badge/react-18-blue)](#)
[![PostgreSQL](https://img.shields.io/badge/postgresql-15-blue)](#)
[![Docker](https://img.shields.io/badge/docker-ready-blue)](#)

---

## 📌 Descrição
O **Interceptor System** é um **MVP** desenvolvido para centralizar e otimizar a gestão operacional e financeira de empresas do setor de segurança e terceirização.  
Ele substitui processos manuais, planilhas e comunicação dispersa por uma plataforma web única, oferecendo **planejamento de escalas, gestão de substituições e relatórios financeiros**.

**Autor:** João Calsavara

 **Status do Projeto:**
 🚧 Em desenvolvimento — aplicando boas práticas como **testes automatizados**, **tratamento de erros**, **Arquitetura Limpa (Clean Architecture)** e **Docker Compose**.

---

## 📚 Documentos
Documentos utilizados para desenvolver o sistema:
- **Documento Visão:** [LINK](https://docs.google.com/document/d/1DBr0ZGBdf_LEqdu4ivpYspxIWfWR1qP1r9c7V68j8Ms/edit?tab=t.0)
- **Diagrama ME-R:** [LINK](https://app.diagrams.net/#G1Bsk7YUn12gBGZ09SGNztIV5Kc54X7zIB#%7B%22pageId%22%3A%22R2lEEEUBdFMjLlhIrx00%22%7D)
- **Documentação API:** Em desenvolvimento.
---

## ⚠️ Problema Central
Atualmente, empresas de segurança e terceirização enfrentam:
- **Ineficiência Operacional:** Escalas de trabalho e substituições manuais consomem tempo excessivo.
- **Falta de Visibilidade Financeira:** Dificuldade para saber a rentabilidade real de cada contrato.
- **Risco Elevado de Erros:** Dependência de processos manuais para cálculos de faturamento e custos.

---

## 💡 Soluções Propostas
1. **Modelo de Negócio Pragmático:** Custos e receitas baseados por turno de 12 horas, simplificando cálculos.
2. **Arquitetura Limpa (Clean Architecture):** Separação entre regras de negócio e tecnologia para maior escalabilidade.
3. **Tecnologia Moderna e Robusta:**
   - Backend: Java 21 + Spring Boot 3
   - Frontend: React + Tailwind CSS
   - Banco de Dados: PostgreSQL
   - Containerização: Docker e Docker Compose

---

## 📈 Possível Impacto
- **Eficiência Operacional:** Substituições feitas em minutos, não horas.
- **Inteligência Financeira:** Rentabilidade por contrato em tempo real.
- **Valorização Estratégica:** Diferencial competitivo e percepção de profissionalismo.

---

## 🗂 Entidades Principais
- **Funcionario:** Colaborador CLT, Freelancer ou Terceirizado.
- **Condominio:** Cliente do sistema.
- **Contrato:** Regras comerciais e valor de diária por turno.
- **PostoDeTrabalho:** Vaga de trabalho num condomínio.
- **Alocacao:** Conecta Funcionário a PostoDeTrabalho numa data específica.

---

## ✨ Funcionalidades Principais (MVP)
- 📅 **Planejamento de Escala:** Interface visual para criar, editar e visualizar escalas mensais.
- 🔄 **Gestão de Substituições:** Lista de substitutos disponíveis ordenada por custo.
- 📊 **Relatórios Financeiros:** Totais e detalhamento de faturamento, gasto e lucro por condomínio.

---

## 🖥 Telas Principais
1. **Dashboard de Planejamento de Escala**
2. **Tela de Gestão de Substituições**
3. **Tela de Relatório Financeiro**
4. **Gestão de Funcionários**
5. **Gestão de Condomínios e Contratos**

---

## 📋 Backlog do Projeto (Épicos e Histórias de Usuário)

### Épico 1 – Gestão de Cadastros Fundamentais
- FUNC-01: CRUD de Condomínios.
- FUNC-02: CRUD de Contratos.
- FUNC-03: CRUD de Postos de Trabalho.
- FUNC-04: CRUD de Funcionários.

### Épico 2 – Gestão do Planejamento de Escala
- PLAN-01: Criar Alocação.
- PLAN-02: Validar Regras de Negócio.
- PLAN-03: Visão de Calendário Mensal.
- PLAN-04: Editar ou Cancelar Alocação.

### Épico 3 – Gestão de Substituições
- SUB-01: Marcar Alocação como "Falta".
- SUB-02: Gerar Lista de Substitutos.
- SUB-03: Confirmar Substituição.

### Épico 4 – Relatórios Financeiros
- REL-01: Totais de Faturamento, Gasto e Lucro.
- REL-02: Detalhamento por Condomínio.
- REL-03 (Opcional): Exportação CSV/PDF.

---

## 🛠 Stack Tecnológica
- **Backend:** Java 21, Spring Boot 3, Spring Data JPA
- **Frontend:** React 18, Tailwind CSS
- **Banco de Dados:** PostgreSQL 15
- **Containerização:** Docker, Docker Compose
- **Nuvem:** AWS, EC2, Bucket

---

## 📦 Instalação e Execução

### Pré-requisitos
- Docker e Docker Compose instalados
- Java 21 (para rodar backend localmente sem Docker)
- Node.js 18+ (para rodar frontend localmente)

### Passos
```bash
# Clone o repositório
git clone https://link-para-o-projeto.git

# Entre no diretório do backend
cd intercetor/api

# Suba com Docker Compose (recomendado)
docker-compose up --build
