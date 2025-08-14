package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.FuncionarioRequestDTO;
import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.domain.vo.CPF;
import com.interceptorsystem.api.domain.vo.Celular;
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
            CPF cpf = new CPF(data.cpf());

            funcionarioRepository.findByCpf(cpf).ifPresent(funcionario -> {
                throw new FuncionarioJaExisteException("Funcionário com o CPF " + cpf.getValor() + " já cadastrado.");
            });

            FuncionarioEntity newFuncionario = new FuncionarioEntity();
            newFuncionario.setNome(data.nome());
            newFuncionario.setCpf(cpf);
            newFuncionario.setCelular(new Celular(data.celular()));
            newFuncionario.setStatusFuncionario(data.statusFuncionario());
            newFuncionario.setTipoEscala(data.tipoEscala());
            newFuncionario.setTipoFuncionario(data.tipoFuncionario());

            return funcionarioRepository.save(newFuncionario);

        } catch (FuncionarioJaExisteException e) {
            throw e; // Relança a exceção específica para o GlobalExceptionHandler
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao criar o funcionário.", e);
        }
    }
}
