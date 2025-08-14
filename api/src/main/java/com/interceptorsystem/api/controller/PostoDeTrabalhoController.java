package com.interceptorsystem.api.controller;

import com.interceptorsystem.api.dto.PostoDeTrabalhoRequestDTO;
import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import com.interceptorsystem.api.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posto-de-trabalho")
@RequiredArgsConstructor
public class PostoDeTrabalhoController {

    private final CreatePostoDeTrabalhoUseCase createPostoDeTrabalhoUseCase;
    private final GetAllPostoDeTrabalhoUseCase getAllPostoDeTrabalhoUseCase;
    private final GetPostoDeTrabalhoByIdUseCase getPostoDeTrabalhoByIdUseCase;
    private final UpdatePostoDeTrabalhoUseCase updatePostoDeTrabalhoUseCase;
    private final DeletePostoDeTrabalhoUseCase deletePostoDeTrabalhoUseCase;

    @PostMapping
    public ResponseEntity<PostoDeTrabalhoEntity> createPostoDeTrabalho(@RequestBody PostoDeTrabalhoRequestDTO data) throws Exception {
        PostoDeTrabalhoEntity newPosto = createPostoDeTrabalhoUseCase.execute(data);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPosto.getId())
                .toUri();
        return ResponseEntity.created(location).body(newPosto);
    }

    @GetMapping
    public ResponseEntity<List<PostoDeTrabalhoEntity>> getAllPostoDeTrabalho() throws Exception {
        return ResponseEntity.ok(getAllPostoDeTrabalhoUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostoDeTrabalhoEntity> getPostoDeTrabalhoById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(getPostoDeTrabalhoByIdUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostoDeTrabalhoEntity> updatePostoDeTrabalho(@PathVariable UUID id, @RequestBody PostoDeTrabalhoRequestDTO data) throws Exception {
        return ResponseEntity.ok(updatePostoDeTrabalhoUseCase.execute(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostoDeTrabalho(@PathVariable UUID id) throws Exception {
        deletePostoDeTrabalhoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
