package com.interceptorsystem.api.repository;

import com.interceptorsystem.api.domain.vo.CPF;
import com.interceptorsystem.api.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, UUID> {
    // O Spring Data JPA cria a query automaticamente com base no nome do método.
    // Ele sabe como buscar dentro do Value Object CPF.
    Optional<FuncionarioEntity> findByCpf(CPF cpf);
}
