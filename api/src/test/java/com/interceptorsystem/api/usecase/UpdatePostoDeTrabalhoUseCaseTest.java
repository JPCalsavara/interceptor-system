package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.dto.PostoDeTrabalhoRequestDTO;
import com.interceptorsystem.api.entity.PostoDeTrabalhoEntity;
import com.interceptorsystem.api.exception.PostoDeTrabalhoNaoEncontradoException;
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
public class UpdatePostoDeTrabalhoUseCaseTest {

    @Mock
    private PostoDeTrabalhoRepository postoDeTrabalhoRepository;
    @InjectMocks
    private UpdatePostoDeTrabalhoUseCase updatePostoDeTrabalhoUseCase;

    @Test
    @DisplayName("Deve atualizar um posto de trabalho com sucesso")
    void updatePosto_withValidId_shouldSucceed() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        var request = new PostoDeTrabalhoRequestDTO("Descrição Atualizada", LocalTime.of(9, 0), LocalTime.of(17, 0), null);
        when(postoDeTrabalhoRepository.findById(id)).thenReturn(Optional.of(new PostoDeTrabalhoEntity()));
        when(postoDeTrabalhoRepository.save(any(PostoDeTrabalhoEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        // Action
        PostoDeTrabalhoEntity result = updatePostoDeTrabalhoUseCase.execute(id, request);

        // Assert
        assertNotNull(result);
        assertEquals("Descrição Atualizada", result.getDescricao());
        verify(postoDeTrabalhoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar posto inexistente")
    void updatePosto_withInvalidId_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        var request = new PostoDeTrabalhoRequestDTO("Qualquer", LocalTime.now(), LocalTime.now(), null);
        when(postoDeTrabalhoRepository.findById(id)).thenReturn(Optional.empty());

        // Action & Assert
        assertThrows(PostoDeTrabalhoNaoEncontradoException.class, () -> {
            updatePostoDeTrabalhoUseCase.execute(id, request);
        });
        verify(postoDeTrabalhoRepository, never()).save(any());
    }
}
