package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateCondominioUseCase {

    public final CondominioRepository condominioRepository;

    public CondominioEntity execute(CondominioRequestDTO data, UUID id){
        CondominioEntity condominio = condominioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Condomínio não encontrado com o ID: " + id));
        ;
        condominio.setNome(data.nome());
        condominio.setCnpj(data.cnpj());
        condominio.setStatus(data.status());

        return condominioRepository.save(condominio);
    }

}
