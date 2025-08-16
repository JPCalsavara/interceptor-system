package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.domain.vo.Endereco;
import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateCondominioUseCase {

    private final CondominioRepository condominioRepository;

    public CondominioEntity execute(UUID id, CondominioRequestDTO data) throws Exception {
        try {
            Optional<CondominioEntity> optionalCondominio = condominioRepository.findById(id);
            if (optionalCondominio.isEmpty()) {
                throw new CondominioNaoEncontradoException("Condomínio com o ID " + id + " não encontrado.");
            }

            CondominioEntity condominioToUpdate = optionalCondominio.get();

            // Cria o novo Value Object de Endereço a partir dos dados do DTO
            Endereco novoEndereco = new Endereco(
                    data.logradouro(),
                    data.numero(),
                    data.complemento(),
                    data.bairro(),
                    data.cidade(),
                    data.estado(),
                    data.cep()
            );

            // Atualiza os campos da entidade
            condominioToUpdate.setNome(data.nome());
            condominioToUpdate.setStatus(data.status());
            condominioToUpdate.setEndereco(novoEndereco);
            // O CNPJ geralmente não é alterado em uma atualização para manter a integridade

            return condominioRepository.save(condominioToUpdate);

        } catch (CondominioNaoEncontradoException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new Exception("Dados inválidos fornecidos: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao atualizar o condomínio.", e);
        }
    }
}
