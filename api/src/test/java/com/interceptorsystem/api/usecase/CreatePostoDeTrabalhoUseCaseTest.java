package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.PostoDeTrabalhoRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
import com.interceptorsystem.api.repository.PostoDeTrabalhoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePostoDeTrabalhoUseCaseTest {

    @Mock
    private PostoDeTrabalhoRepository postoDeTrabalhoRepository;
    @Mock
    private CondominioRepository condominioRepository;
    @InjectMocks
    private CreatePostoDeTrabalhoUseCase createPostoDeTrabalhoUseCase;

    @Test
    @DisplayName("Deve criar um posto de trabalho com sucesso")
    void createPosto_withValidCondominio_shouldSucceed() throws Exception {
        // Arrange
        var request = new PostoDeTrabalhoRequestDTO("Portaria Principal", LocalTime.of(8, 0), LocalTime.of(18, 0), UUID.randomUUID());
        when(condominioRepository.findById(request.condominioId())).thenReturn(Optional.of(new CondominioEntity()));
        when(postoDeTrabalhoRepository.save(any(PostoDeTrabalhoEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        // Action
        PostoDeTrabalhoEntity result = createPostoDeTrabalhoUseCase.execute(request);

        // Assert
        assertNotNull(result);
        assertEquals("Portaria Principal", result.getDescricao());
        verify(postoDeTrabalhoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção se o condomínio não for encontrado")
    void createPosto_withInvalidCondominio_shouldThrowException() {
        // Arrange
        var request = new PostoDeTrabalhoRequestDTO("Portaria", LocalTime.now(), LocalTime.now(), UUID.randomUUID());
        when(condominioRepository.findById(request.condominioId())).thenReturn(Optional.empty());

        // Action & Assert
        assertThrows(CondominioNaoEncontradoException.class, () -> {
            createPostoDeTrabalhoUseCase.execute(request);
        });
        verify(postoDeTrabalhoRepository, never()).save(any());
    }
}
