package com.interceptorsystem.api.controller;

import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.usecase.CreateCondominioUseCase;
import com.interceptorsystem.api.usecase.DeleteCondominioUseCase;
import com.interceptorsystem.api.usecase.GetAllCondominioUseCase;
import com.interceptorsystem.api.usecase.UpdateCondominioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/condominio")
@RequiredArgsConstructor
public class CondominioController {

    private final CreateCondominioUseCase createCondominioUseCase;
    private final UpdateCondominioUseCase updateCondominioUseCase;
    private final GetAllCondominioUseCase getAllCondominioUseCase;
    private final DeleteCondominioUseCase deleteCondominioUseCase;

    @PostMapping
    public ResponseEntity<CondominioEntity> createCondominio(@RequestBody CondominioRequestDTO data) throws Exception {
        // 1. Executa o caso de uso para criar a entidade.
        CondominioEntity newCondominio = createCondominioUseCase.execute(data);

        // 2. Constrói a URI para o novo recurso criado.
        //    Isso é essencial para o cabeçalho "Location" na resposta 201.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCondominio.getId())
                .toUri();

        // 3. Retorna a ResponseEntity com o status 201 Created, a URI no cabeçalho Location
        //    e a entidade criada no corpo da resposta.
        return ResponseEntity.created(location).body(newCondominio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominioEntity> updateCondominio(@PathVariable UUID id, @RequestBody CondominioRequestDTO data) throws Exception {
        CondominioEntity updatedCondominio = updateCondominioUseCase.execute(id, data);
        return ResponseEntity.ok(updatedCondominio);
    }

    @GetMapping
    public ResponseEntity<List<CondominioEntity>> getAllCondominios() {
        List<CondominioEntity> condominios = getAllCondominioUseCase.execute();
        return ResponseEntity.ok(condominios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondominio(@PathVariable UUID id) {
        deleteCondominioUseCase.execute(id);
        return ResponseEntity.noContent().build(); // Retorna status 204 No Content
    }
}
