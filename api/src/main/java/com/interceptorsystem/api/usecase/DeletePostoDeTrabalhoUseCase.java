package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.exception.PostoDeTrabalhoNaoEncontradoException;
import com.interceptorsystem.api.repository.PostoDeTrabalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletePostoDeTrabalhoUseCase {
    private final PostoDeTrabalhoRepository postoDeTrabalhoRepository;

    public void execute(UUID id) throws Exception {
        try {
            if (!postoDeTrabalhoRepository.existsById(id)) {
                throw new PostoDeTrabalhoNaoEncontradoException("Não foi possível deletar. Posto de trabalho com ID " + id + " não encontrado.");
            }
            postoDeTrabalhoRepository.deleteById(id);
        } catch (PostoDeTrabalhoNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao deletar o posto de trabalho.", e);
        }
    }
}
