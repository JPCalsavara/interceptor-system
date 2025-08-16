package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.domain.vo.CNPJ;
import com.interceptorsystem.api.domain.vo.Endereco;
import com.interceptorsystem.api.exception.CondominioJaExisteException;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCondominioUseCase {

    private final CondominioRepository condominioRepository;

    public CondominioEntity execute(CondominioRequestDTO data) throws Exception {
        try {
            CNPJ cnpj = new CNPJ(data.cnpj());

            condominioRepository.findByCnpj(cnpj).ifPresent(condominio -> {
                throw new CondominioJaExisteException("Condomínio com o CNPJ " + data.cnpj() + " já cadastrado.");
            });

            Endereco endereco = new Endereco(
                    data.logradouro(), data.numero(), data.complemento(), data.bairro(),
                    data.cidade(), data.estado(), data.cep()
            );

            CondominioEntity newCondominio = new CondominioEntity();
            newCondominio.setNome(data.nome());
            newCondominio.setStatus(data.status());
            newCondominio.setCnpj(cnpj);
            newCondominio.setEndereco(endereco);

            return condominioRepository.save(newCondominio);

        } catch (CondominioJaExisteException e) {
            // Captura a exceção de negócio e a relança para o GlobalExceptionHandler
            throw e;
        } catch (IllegalArgumentException e) {
            // Captura erros de validação dos Value Objects
            throw new Exception("Dados inválidos fornecidos: " + e.getMessage(), e);
        } catch (Exception e) {
            // Captura qualquer outro erro inesperado
            throw new Exception("Ocorreu um erro inesperado ao criar o condomínio.", e);
        }
    }
}
