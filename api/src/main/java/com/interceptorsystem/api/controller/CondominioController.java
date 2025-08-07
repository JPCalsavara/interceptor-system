package com.interceptorsystem.api.controller;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.usecase.CreateCondominioUseCase;
import com.interceptorsystem.api.usecase.GetAllCondominioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/condominio")
@RequiredArgsConstructor
public class CondominioController {

    private final GetAllCondominioUseCase getAllCondominioUseCase;
    private final CreateCondominioUseCase createCondominioUseCase;

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
}
