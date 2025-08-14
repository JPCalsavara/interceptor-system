package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.FuncionarioRequestDTO;
import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.domain.vo.Celular;
import com.interceptorsystem.api.exception.FuncionarioNaoEncontradoException;
import com.interceptorsystem.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateFuncionarioUseCase {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity execute(UUID id, FuncionarioRequestDTO data) throws Exception {
        try {
            Optional<FuncionarioEntity> optionalFuncionario = funcionarioRepository.findById(id);
            if (optionalFuncionario.isEmpty()) {
                throw new FuncionarioNaoEncontradoException("Funcionário com o ID " + id + " não encontrado.");
            }

            FuncionarioEntity funcionarioToUpdate = optionalFuncionario.get();
            funcionarioToUpdate.setNome(data.nome());
            funcionarioToUpdate.setCelular(new Celular(data.celular()));
            funcionarioToUpdate.setStatusFuncionario(data.statusFuncionario());
            funcionarioToUpdate.setTipoEscala(data.tipoEscala());
            funcionarioToUpdate.setTipoFuncionario(data.tipoFuncionario());

            return funcionarioRepository.save(funcionarioToUpdate);
        } catch (FuncionarioNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao atualizar o funcionário.", e);
        }
    }
}
