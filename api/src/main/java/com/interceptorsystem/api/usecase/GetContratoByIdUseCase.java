package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.exception.ContratoNaoEncontradoException;
import com.interceptorsystem.api.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetContratoByIdUseCase {

    private final ContratoRepository contratoRepository;

    public ContratoEntity execute(UUID id) throws Exception {
        try {
            // 1. Busca o contrato pelo ID, recebendo um Optional.
            Optional<ContratoEntity> optionalContrato = contratoRepository.findById(id);

            // 2. Verifica se o Optional está vazio.
            if (optionalContrato.isEmpty()) {
                // Se estiver vazio, lança a exceção específica.
                throw new ContratoNaoEncontradoException("Contrato com o ID " + id + " não encontrado.");
            }

            // 3. Se o Optional contiver um valor, retorna a entidade.
            return optionalContrato.get();

        } catch (ContratoNaoEncontradoException e) {
            // 4. Captura a exceção específica e a relança para ser tratada pelo GlobalExceptionHandler.
            throw e;
        } catch (Exception e) {
            // 5. Captura qualquer outro erro inesperado e o "embrulha" numa exceção genérica.
            throw new Exception("Ocorreu um erro inesperado ao buscar o contrato.", e);
        }
    }
}
