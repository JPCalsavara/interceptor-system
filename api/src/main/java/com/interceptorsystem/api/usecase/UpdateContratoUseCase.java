package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusContrato;
import com.interceptorsystem.api.dto.ContratoRequestDTO;
import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.exception.ContratoNaoEncontradoException;
import com.interceptorsystem.api.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateContratoUseCase {

    private final ContratoRepository contratoRepository;

    public ContratoEntity execute(UUID id, ContratoRequestDTO data) throws Exception {
        try {
            // 1. Busca o contrato pelo ID, recebendo um Optional.
            Optional<ContratoEntity> optionalContrato = contratoRepository.findById(id);

            // 2. Verifica se o contrato não foi encontrado.
            if (optionalContrato.isEmpty()) {
                // Se estiver vazio, lança a exceção específica.
                throw new ContratoNaoEncontradoException("Contrato com o ID " + id + " não encontrado.");
            }

            // 3. Se foi encontrado, atualiza os campos da entidade.
            ContratoEntity contratoToUpdate = optionalContrato.get();
            contratoToUpdate.setDescricao(data.descricao());
            contratoToUpdate.setValorTotal(data.valorTotal());
            contratoToUpdate.setValorDiariaCobrada(data.valorDiaria());
            contratoToUpdate.setDataInicio(data.dataInicio());
            contratoToUpdate.setDataFim(data.dataFim());
            contratoToUpdate.setStatus((StatusContrato) data.status());

            // 4. Salva a entidade atualizada e a retorna.
            return contratoRepository.save(contratoToUpdate);

        } catch (ContratoNaoEncontradoException e) {
            // 5. Captura a exceção específica e a relança para ser tratada pelo GlobalExceptionHandler.
            throw e;
        } catch (Exception e) {
            // 6. Captura qualquer outro erro inesperado e o "embrulha" numa exceção genérica.
            throw new Exception("Ocorreu um erro inesperado ao atualizar o contrato.", e);
        }
    }
}
