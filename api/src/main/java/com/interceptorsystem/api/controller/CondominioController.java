package com.interceptorsystem.api.controller;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.usecase.CreateCondominioUseCase;
import com.interceptorsystem.api.usecase.GetAllCondominioUseCase;
import com.interceptorsystem.api.usecase.UpdateCondominioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/condominio")
@RequiredArgsConstructor
public class CondominioController {

    private final GetAllCondominioUseCase getAllCondominioUseCase;
    private final CreateCondominioUseCase createCondominioUseCase;
    private final UpdateCondominioUseCase updateCondominioUseCase;

    @GetMapping
    public ResponseEntity<List<CondominioEntity>> getAllCondominios(){
        List<CondominioEntity> condominios = getAllCondominioUseCase.execute();
        return ResponseEntity.ok(condominios);
    }

    @PostMapping
    public ResponseEntity<CondominioEntity> createCondominio(
            @RequestBody CondominioRequestDTO condominioRequestDTO
            ){
        CondominioEntity newCondominio = createCondominioUseCase.execute(condominioRequestDTO);
        return ResponseEntity.ok(newCondominio);
    }

    @PutMapping
    public ResponseEntity<CondominioEntity> updateCondominio(
            @RequestBody CondominioRequestDTO condominioRequestDTO, UUID id
    ){
        CondominioEntity newCondominio = updateCondominioUseCase.execute(condominioRequestDTO, id);
        return ResponseEntity.ok(newCondominio);
    }
}
