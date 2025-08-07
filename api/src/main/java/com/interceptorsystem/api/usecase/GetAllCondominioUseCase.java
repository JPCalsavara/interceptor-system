package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCondominioUseCase {

    private final CondominioRepository condominioRepository;

    public List<CondominioEntity> execute(){
        return condominioRepository.findAll();
    }
}
