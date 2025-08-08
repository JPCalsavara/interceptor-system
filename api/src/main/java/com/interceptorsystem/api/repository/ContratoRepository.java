package com.interceptorsystem.api.repository;

import com.interceptorsystem.api.entity.ContratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContratoRepository extends JpaRepository<ContratoEntity, UUID> {

}
