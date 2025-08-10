package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateCondominioUseCase {

    public final CondominioRepository condominioRepository;

    public CondominioEntity execute(UUID id, CondominioRequestDTO data) throws Exception {
        try {
            Optional<CondominioEntity> optionalCondominio = condominioRepository.findById(id);

            if (optionalCondominio.isPresent()) {
                CondominioEntity condominioToUpdate = optionalCondominio.get();
                condominioToUpdate.setNome(data.nome());
                condominioToUpdate.setStatus(data.status());
                return condominioRepository.save(condominioToUpdate);
            } else {
                // Lança a exceção específica
                throw new CondominioNaoEncontradoException("Condomínio com o ID " + id + " não encontrado.");
            }

        } catch (CondominioNaoEncontradoException e) {
            // 1. Captura a exceção específica de "não encontrado".
            //    Ao relançá-la aqui, você permite que o Spring a veja e retorne o status 404.
            throw e;

        } catch (Exception e) {
            // 2. Captura qualquer outra exceção inesperada (ex: erro de banco de dados).
            //    Aqui você a "embrulha" em uma exceção genérica.
            throw new Exception("Ocorreu um erro inesperado ao atualizar o condomínio.", e);
        }
    }

}
