package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import com.interceptorsystem.api.repository.PostoDeTrabalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPostoDeTrabalhoUseCase {
    private final PostoDeTrabalhoRepository postoDeTrabalhoRepository;

    public List<PostoDeTrabalhoEntity> execute() throws Exception {
        try {
            return postoDeTrabalhoRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao buscar todos os postos de trabalho.", e);
        }
    }
}
