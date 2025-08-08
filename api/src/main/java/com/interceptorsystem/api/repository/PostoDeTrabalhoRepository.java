package com.interceptorsystem.api.repository;

import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostoDeTrabalhoRepository extends JpaRepository<PostoDeTrabalhoEntity, UUID> {
}
