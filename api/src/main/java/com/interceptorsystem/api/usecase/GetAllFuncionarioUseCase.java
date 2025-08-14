package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllFuncionarioUseCase {

    private final FuncionarioRepository funcionarioRepository;

    public List<FuncionarioEntity> execute() throws Exception {
        try {
            return funcionarioRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro ao buscar todos os funcionários.", e);
        }
    }
}
