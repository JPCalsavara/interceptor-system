package com.interceptorsystem.api.controller;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.usecase.CreateCondominioUseCase;
import com.interceptorsystem.api.usecase.DeleteCondominioUseCase;
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
    private final DeleteCondominioUseCase deleteCondominioUseCase;

    @GetMapping
    public ResponseEntity<List<CondominioEntity>> getAllCondominios(){
        List<CondominioEntity> condominios = getAllCondominioUseCase.execute();
        return ResponseEntity.ok(condominios);
    }

    @PostMapping
    public ResponseEntity<CondominioEntity> createCondominio(
            @RequestBody CondominioRequestDTO condominioRequestDTO
            ) throws Exception {
        CondominioEntity newCondominio = createCondominioUseCase.execute(condominioRequestDTO);
        return ResponseEntity.ok(newCondominio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominioEntity> updateCondominio(
            @RequestBody CondominioRequestDTO condominioRequestDTO,
            @PathVariable UUID id
    ) throws Exception {
        CondominioEntity newCondominio = updateCondominioUseCase.execute( id, condominioRequestDTO);
        return ResponseEntity.ok(newCondominio);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCondominio(
            @PathVariable UUID id
    ){
        deleteCondominioUseCase.execute(id);
        return ResponseEntity.ok("A condominio foi deletado de ID =" + id);
    }
}
