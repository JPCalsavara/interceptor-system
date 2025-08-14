package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.exception.FuncionarioNaoEncontradoException;
import com.interceptorsystem.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetFuncionarioByIdUseCase {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity execute(UUID id) throws Exception {
        try {
            Optional<FuncionarioEntity> optionalFuncionario = funcionarioRepository.findById(id);
            if (optionalFuncionario.isEmpty()) {
                throw new FuncionarioNaoEncontradoException("Funcionário com o ID " + id + " não encontrado.");
            }
            return optionalFuncionario.get();
        } catch (FuncionarioNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao buscar o funcionário.", e);
        }
    }
}
