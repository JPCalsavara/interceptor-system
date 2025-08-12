package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.exception.ContratoNaoEncontradoException;
import com.interceptorsystem.api.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteContratoUseCase {

    private final ContratoRepository contratoRepository;

    public void execute(UUID id) {
        // 1. Verifica se o contrato com o ID fornecido existe.
        if (!contratoRepository.existsById(id)) {
            // 2. Se não existir, lança a exceção específica.
            throw new ContratoNaoEncontradoException("Não foi possível deletar. Contrato com o ID " + id + " não encontrado.");
        }

        // 3. Se existir, deleta o contrato.
        contratoRepository.deleteById(id);
    }
}
