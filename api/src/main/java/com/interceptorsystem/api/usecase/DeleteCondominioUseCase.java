package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCondominioUseCase {

    private final CondominioRepository condominioRepository;

    public void execute(UUID id) {
        // 1. Verifica se o condomínio com o ID fornecido existe.
        if (!condominioRepository.existsById(id)) {
            // 2. Se não existir, lança a exceção específica.
            throw new CondominioNaoEncontradoException("Não foi possível deletar. Condomínio com o ID " + id + " não encontrado.");
        }

        // 3. Se existir, deleta o condomínio.
        condominioRepository.deleteById(id);
    }
}
