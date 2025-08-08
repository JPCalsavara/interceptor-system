package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.AlocacaoEntity;
import com.interceptorsystem.api.repository.AlocacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAlocacaoUseCase {
    private final AlocacaoRepository alocacaoRepository;

    public List<AlocacaoEntity> execute(){
        return alocacaoRepository.findAll();
    }
}
