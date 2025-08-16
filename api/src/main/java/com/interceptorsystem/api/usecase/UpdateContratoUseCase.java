package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.ContratoRequestDTO;
import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.domain.enums.StatusContrato;
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
            Optional<ContratoEntity> optionalContrato = contratoRepository.findById(id);
            if (optionalContrato.isEmpty()) {
                throw new ContratoNaoEncontradoException("Contrato com o ID " + id + " não encontrado.");
            }

            ContratoEntity contratoToUpdate = optionalContrato.get();

            // Atualiza apenas os campos que não são nulos no DTO, permitindo atualizações parciais
            if (data.descricao() != null) {
                contratoToUpdate.setDescricao(data.descricao());
            }
            if (data.valorTotal() != null) {
                contratoToUpdate.setValorTotal(data.valorTotal());
            }
            if (data.valorDiaria() != null) {
                contratoToUpdate.setValorDiariaCobrada(data.valorDiaria());
            }
            if (data.dataInicio() != null) {
                contratoToUpdate.setDataInicio(data.dataInicio());
            }
            if (data.dataFim() != null) {
                contratoToUpdate.setDataFim(data.dataFim());
            }
            if (data.statusContrato() != null && !data.statusContrato().isBlank()) {
                contratoToUpdate.setStatus(data.statusContrato());
            }

            return contratoRepository.save(contratoToUpdate);

        } catch (ContratoNaoEncontradoException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new Exception("Status de contrato inválido: " + data.statusContrato());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao atualizar o contrato.", e);
        }
    }
}
