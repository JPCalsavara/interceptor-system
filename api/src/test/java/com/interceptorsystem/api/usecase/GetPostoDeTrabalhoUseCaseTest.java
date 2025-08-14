package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import com.interceptorsystem.api.exception.PostoDeTrabalhoNaoEncontradoException;
import com.interceptorsystem.api.repository.PostoDeTrabalhoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPostoDeTrabalhoUseCaseTest {

    @Mock
    private PostoDeTrabalhoRepository postoDeTrabalhoRepository;

    // Injetando os dois UseCases que serão testados nesta classe
    @InjectMocks
    private GetAllPostoDeTrabalhoUseCase getAllPostoDeTrabalhoUseCase;

    @InjectMocks
    private GetPostoDeTrabalhoByIdUseCase getPostoDeTrabalhoByIdUseCase;

    //--- Testes para GetAllPostosDeTrabalhoUseCase ---

    @Test
    @DisplayName("GetAll: Deve retornar uma lista de postos de trabalho")
    void getAll_shouldReturnList() throws Exception {
        // Arrange
        when(postoDeTrabalhoRepository.findAll()).thenReturn(List.of(new PostoDeTrabalhoEntity(), new PostoDeTrabalhoEntity()));

        // Action
        List<PostoDeTrabalhoEntity> result = getAllPostoDeTrabalhoUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("GetAll: Deve retornar uma lista vazia se não houver postos")
    void getAll_whenEmpty_shouldReturnEmptyList() throws Exception {
        // Arrange
        when(postoDeTrabalhoRepository.findAll()).thenReturn(Collections.emptyList());

        // Action
        List<PostoDeTrabalhoEntity> result = getAllPostoDeTrabalhoUseCase.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    //--- Testes para GetPostoDeTrabalhoByIdUseCase ---

    @Test
    @DisplayName("GetById: Deve retornar um posto de trabalho quando o ID for válido")
    void getById_withValidId_shouldReturnPosto() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        PostoDeTrabalhoEntity mockPosto = new PostoDeTrabalhoEntity();
        mockPosto.setId(id);

        when(postoDeTrabalhoRepository.findById(id)).thenReturn(Optional.of(mockPosto));

        // Action
        PostoDeTrabalhoEntity result = getPostoDeTrabalhoByIdUseCase.execute(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    @DisplayName("GetById: Deve lançar PostoDeTrabalhoNaoEncontradoException quando o ID não existir")
    void getById_withInvalidId_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(postoDeTrabalhoRepository.findById(id)).thenReturn(Optional.empty());

        // Action & Assert
        assertThrows(PostoDeTrabalhoNaoEncontradoException.class, () -> {
            getPostoDeTrabalhoByIdUseCase.execute(id);
        });
    }
}
