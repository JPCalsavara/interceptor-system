package com.interceptorsystem.api.controller;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.AlocacaoEntity;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/alocacao")
@RequiredArgsConstructor
public class AlocacaoController {

    private final GetAllAlocacaoUseCase getAllAlocacaoUseCase;

    @GetMapping()
    public ResponseEntity<List<AlocacaoEntity>> getAllAlocacoes(){
        List<AlocacaoEntity> alocacoes = getAllAlocacaoUseCase.execute();
        return ResponseEntity.ok(alocacoes);
    }
}



