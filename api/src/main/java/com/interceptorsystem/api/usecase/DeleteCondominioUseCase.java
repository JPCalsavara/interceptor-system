package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.repository.CondominioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCondominioUseCase {
    public final CondominioRepository condominioRepository;

    public void execute(UUID id){
         condominioRepository.deleteById(id);
    }
}
