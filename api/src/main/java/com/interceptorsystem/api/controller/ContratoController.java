package com.interceptorsystem.api.controller;

import com.interceptorsystem.api.dto.ContratoRequestDTO;
import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.usecase.*; // Supondo que você tenha um pacote para todos os use cases
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contrato")
@RequiredArgsConstructor
public class ContratoController {

    // Injeção de todos os casos de uso necessários para o CRUD de Contrato
    private final CreateContratoUseCase createContratoUseCase;
    private final UpdateContratoUseCase updateContratoUseCase;
    private final DeleteContratoUseCase deleteContratoUseCase;
    private final GetAllContratoUseCase getAllContratoUseCase;
    private final GetContratoByIdUseCase getContratoByIdUseCase;

    @PostMapping
    public ResponseEntity<ContratoEntity> createContrato(@RequestBody ContratoRequestDTO data) throws Exception {
        ContratoEntity newContrato = createContratoUseCase.execute(data);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newContrato.getId())
                .toUri();
        return ResponseEntity.created(location).body(newContrato);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoEntity> getContratoById(@PathVariable UUID id) throws Exception {
        ContratoEntity contrato = getContratoByIdUseCase.execute(id);
        return ResponseEntity.ok(contrato);
    }

    @GetMapping
    public ResponseEntity<List<ContratoEntity>> getAllContrato() {
        List<ContratoEntity> contratos = getAllContratoUseCase.execute();
        return ResponseEntity.ok(contratos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoEntity> updateContrato(@PathVariable UUID id, @RequestBody ContratoRequestDTO data) throws Exception {
        ContratoEntity updatedContrato = updateContratoUseCase.execute(id, data);
        return ResponseEntity.ok(updatedContrato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable UUID id) {
        deleteContratoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
