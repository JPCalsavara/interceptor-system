package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllContratoUseCase {

    private final ContratoRepository contratoRepository;

    public List<ContratoEntity> execute() {
        return contratoRepository.findAll();
    }
}
