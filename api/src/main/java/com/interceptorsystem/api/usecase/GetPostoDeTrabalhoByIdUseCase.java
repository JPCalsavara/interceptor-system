package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import com.interceptorsystem.api.exception.PostoDeTrabalhoNaoEncontradoException;
import com.interceptorsystem.api.repository.PostoDeTrabalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetPostoDeTrabalhoByIdUseCase {
    private final PostoDeTrabalhoRepository postoDeTrabalhoRepository;

    public PostoDeTrabalhoEntity execute(UUID id) throws Exception {
        try {
            // 1. Busca o posto de trabalho e armazena em um Optional
            Optional<PostoDeTrabalhoEntity> postoOptional = postoDeTrabalhoRepository.findById(id);

            // 2. Verifica se o Optional está vazio
            if (postoOptional.isEmpty()) {
                // Se estiver, lança a exceção
                throw new PostoDeTrabalhoNaoEncontradoException("Posto de trabalho com ID " + id + " não encontrado.");
            }

            // 3. Se não, retorna a entidade contida no Optional
            return postoOptional.get();

        } catch (PostoDeTrabalhoNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao buscar o posto de trabalho.", e);
        }
    }
}
