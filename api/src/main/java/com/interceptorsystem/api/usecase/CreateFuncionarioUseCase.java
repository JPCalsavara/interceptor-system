package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.FuncionarioRequestDTO;
import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.domain.vo.CPF;
import com.interceptorsystem.api.domain.vo.Celular;
import com.interceptorsystem.api.domain.vo.Endereco;
import com.interceptorsystem.api.exception.FuncionarioJaExisteException;
import com.interceptorsystem.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFuncionarioUseCase {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity execute(FuncionarioRequestDTO data) throws Exception {
        try {
            // 1. Cria os Value Objects. A validação acontece aqui, nos construtores.
            CPF cpf = new CPF(data.cpf());
            Celular celular = new Celular(data.celular());
            Endereco endereco = new Endereco(
                    data.logradouro(),
                    data.numero(),
                    data.complemento(),
                    data.bairro(),
                    data.cidade(),
                    data.estado(),
                    data.cep()
            );

            // 2. Verifica se o CPF já existe no banco.
            funcionarioRepository.findByCpf(cpf).ifPresent(funcionario -> {
                throw new FuncionarioJaExisteException("Funcionário com o CPF " + data.cpf() + " já cadastrado.");
            });

            // 3. Cria a entidade principal.
            FuncionarioEntity newFuncionario = new FuncionarioEntity();
            newFuncionario.setNome(data.nome());
            newFuncionario.setStatusFuncionario(data.statusFuncionario());
            newFuncionario.setTipoEscala(data.tipoEscala());
            newFuncionario.setTipoFuncionario(data.tipoFuncionario());

            // 4. Associa os Value Objects à entidade.
            newFuncionario.setCpf(cpf);
            newFuncionario.setCelular(celular);
            newFuncionario.setEndereco(endereco);

            // 5. Salva a nova entidade no banco.
            return funcionarioRepository.save(newFuncionario);

        } catch (FuncionarioJaExisteException e) {
            // Relança a exceção de negócio para o GlobalExceptionHandler tratar.
            throw e;
        } catch (IllegalArgumentException e) {
            // Captura erros de validação dos Value Objects (CPF, Celular, Endereco, etc.).
            throw new Exception("Dados inválidos fornecidos: " + e.getMessage(), e);
        } catch (Exception e) {
            // Captura qualquer outro erro inesperado.
            throw new Exception("Ocorreu um erro inesperado ao criar o funcionário.", e);
        }
    }
}
