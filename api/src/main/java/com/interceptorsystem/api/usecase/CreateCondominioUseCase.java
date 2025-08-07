package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCondominioUseCase {

    private final CondominioRepository condominioRepository;

    public CondominioEntity execute(CondominioRequestDTO data){
        CondominioEntity newCondominio = new CondominioEntity(data.nome(), data.cnpj(), data.status());
        condominioRepository.save(newCondominio);
        return newCondominio;
    }
}
