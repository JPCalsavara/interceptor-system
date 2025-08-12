package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.exception.CondominioJaExisteException;
import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateCondominioUseCase {

    private final CondominioRepository condominioRepository;

    public CondominioEntity execute(CondominioRequestDTO data) throws Exception {
        try {
            // 1. Verifica se já existe um condomínio com o CNPJ fornecido.
            Optional<CondominioEntity> existingCondominio = condominioRepository.findByCnpj(data.cnpj());

            // 2. Se o condomínio já existir, lança a exceção específica.
            if (existingCondominio.isPresent()) {
                throw new CondominioJaExisteException("Condomínio com o CNPJ " + data.cnpj() + " já cadastrado.");
            }

            // 3. Se não existir, cria a nova entidade e a salva no banco.
            CondominioEntity newCondominio = new CondominioEntity(data.nome(), data.cnpj(),data.status());
            return condominioRepository.save(newCondominio);

        } catch (CondominioJaExisteException e) {
            // 1. Captura a exceção específica de "não encontrado".
            //    Ao relançá-la aqui, você permite que o Spring a veja e retorne o status 404.
            throw e;

        } catch (Exception e) {
            // 4. Captura qualquer exceção (incluindo a CondominioJaExisteException)
            // e a "embrulha" em uma Exception genérica antes de relançar.
            // Isso fará com que o teste que espera a exceção específica falhe.
            throw new Exception("Ocorreu um erro ao criar o condomínio: " + e.getMessage(), e);
        }
    }
}
