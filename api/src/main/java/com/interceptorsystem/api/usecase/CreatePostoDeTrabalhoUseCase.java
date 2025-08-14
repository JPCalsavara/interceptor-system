package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.PostoDeTrabalhoRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
import com.interceptorsystem.api.repository.PostoDeTrabalhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreatePostoDeTrabalhoUseCase {
    private final PostoDeTrabalhoRepository postoDeTrabalhoRepository;
    private final CondominioRepository condominioRepository;

    public PostoDeTrabalhoEntity execute(PostoDeTrabalhoRequestDTO data) throws Exception {
        try {
            // Busca a entidade de Condomínio e armazena em um Optional
            Optional<CondominioEntity> optionalCondominio = condominioRepository.findById(data.condominioId());

            // Verifica se o Optional está vazio
            if (optionalCondominio.isEmpty()) {
                // Se estiver vazio, lança a exceção
                throw new CondominioNaoEncontradoException("Condomínio com ID " + data.condominioId() + " não encontrado.");
            }

            // Se não estiver vazio, pega a entidade e continua
            CondominioEntity condominio = optionalCondominio.get();

            PostoDeTrabalhoEntity newPosto = new PostoDeTrabalhoEntity();
            newPosto.setDescricao(data.descricao());
            newPosto.setHorarioInicio(data.horarioInicio());
            newPosto.setHorarioFim(data.horarioFim());
            newPosto.setCondominio(condominio);

            return postoDeTrabalhoRepository.save(newPosto);

        } catch (CondominioNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado ao criar o posto de trabalho.", e);
        }
    }
}
