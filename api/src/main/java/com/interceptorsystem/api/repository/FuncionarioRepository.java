package com.interceptorsystem.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.interceptorsystem.api.entity.FuncionarioEntity;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, UUID> {
}
