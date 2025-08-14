package com.interceptorsystem.api.controller;

import com.interceptorsystem.api.dto.FuncionarioRequestDTO;
import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final CreateFuncionarioUseCase createFuncionarioUseCase;
    private final GetAllFuncionarioUseCase getAllFuncionariosUseCase;
    private final GetFuncionarioByIdUseCase getFuncionarioByIdUseCase;
    private final UpdateFuncionarioUseCase updateFuncionarioUseCase;
    private final DeleteFuncionarioUseCase deleteFuncionarioUseCase;

    @PostMapping
    public ResponseEntity<FuncionarioEntity> createFuncionario(@RequestBody FuncionarioRequestDTO data) throws Exception {
        FuncionarioEntity newFuncionario = createFuncionarioUseCase.execute(data);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFuncionario.getId())
                .toUri();
        return ResponseEntity.created(location).body(newFuncionario);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioEntity>> getAllFuncionarios() throws Exception {
        return ResponseEntity.ok(getAllFuncionariosUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioEntity> getFuncionarioById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(getFuncionarioByIdUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioEntity> updateFuncionario(@PathVariable UUID id, @RequestBody FuncionarioRequestDTO data) throws Exception {
        return ResponseEntity.ok(updateFuncionarioUseCase.execute(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable UUID id) throws Exception {
        deleteFuncionarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
