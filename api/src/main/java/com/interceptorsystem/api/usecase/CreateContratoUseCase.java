package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusContrato;
import com.interceptorsystem.api.dto.ContratoRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
import com.interceptorsystem.api.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateContratoUseCase {

    private final ContratoRepository contratoRepository;
    private final CondominioRepository condominioRepository;

    public ContratoEntity execute(ContratoRequestDTO data) throws Exception {
        try {
            // 1. Busca o condomínio pelo ID fornecido no DTO.
            Optional<CondominioEntity> optionalCondominio = condominioRepository.findById(data.condominioId());

            // 2. Verifica se o condomínio foi encontrado.
            if (optionalCondominio.isEmpty()) {
                // Se não for encontrado, lança a exceção específica.
                throw new CondominioNaoEncontradoException("Condomínio com o ID " + data.condominioId() + " não encontrado. Não é possível criar o contrato.");
            }

            // 3. Se o condomínio for encontrado, cria e associa o novo contrato.
            CondominioEntity condominio = optionalCondominio.get();
            ContratoEntity newContrato = new ContratoEntity();
            newContrato.setDescricao(data.descricao());
            newContrato.setValorTotal(data.valorTotal());
            newContrato.setValorDiariaCobrada(data.valorDiaria());
            newContrato.setDataInicio(data.dataInicio());
            newContrato.setDataFim(data.dataFim());
            newContrato.setStatus((StatusContrato) data.status());
            newContrato.setCondominio(condominio);

            // 4. Salva o novo contrato no banco de dados.
            return contratoRepository.save(newContrato);

        } catch (CondominioNaoEncontradoException e) {
            // 5. Captura a exceção de "não encontrado" e a relança para ser tratada pelo GlobalExceptionHandler.
            throw e;
        } catch (Exception e) {
            // 6. Captura qualquer outro erro inesperado e o "embrulha" numa exceção genérica.
            throw new Exception("Ocorreu um erro inesperado ao criar o contrato.", e);
        }
    }
}
