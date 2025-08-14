package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.PostoDeTrabalhoRequestDTO;
import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import com.interceptorsystem.api.exception.PostoDeTrabalhoNaoEncontradoException;
import com.interceptorsystem.api.repository.PostoDeTrabalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePostoDeTrabalhoUseCase {
    private final PostoDeTrabalhoRepository postoDeTrabalhoRepository;

    public PostoDeTrabalhoEntity execute(UUID id, PostoDeTrabalhoRequestDTO data) throws Exception {
        try {
            Optional<PostoDeTrabalhoEntity> optionalPosto = postoDeTrabalhoRepository.findById(id);
            if (optionalPosto.isEmpty()) {
                throw new PostoDeTrabalhoNaoEncontradoException("Posto de trabalho com ID " + id + " não encontrado.");
            }

            PostoDeTrabalhoEntity postoToUpdate = optionalPosto.get();
            postoToUpdate.setDescricao(data.descricao());
            postoToUpdate.setHorarioInicio(data.horarioInicio());
            postoToUpdate.setHorarioFim(data.horarioFim());

            return postoDeTrabalhoRepository.save(postoToUpdate);
        } catch (PostoDeTrabalhoNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao atualizar o posto de trabalho.", e);
        }
    }
}
