package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.exception.FuncionarioNaoEncontradoException;
import com.interceptorsystem.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteFuncionarioUseCase {

    private final FuncionarioRepository funcionarioRepository;

    public void execute(UUID id) throws Exception {
        try {
            if (!funcionarioRepository.existsById(id)) {
                throw new FuncionarioNaoEncontradoException("Não foi possível deletar. Funcionário com o ID " + id + " não encontrado.");
            }
            funcionarioRepository.deleteById(id);
        } catch (FuncionarioNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao deletar o funcionário.", e);
        }
    }
}
