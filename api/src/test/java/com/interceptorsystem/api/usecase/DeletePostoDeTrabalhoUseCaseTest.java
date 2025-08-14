package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.exception.PostoDeTrabalhoNaoEncontradoException;
import com.interceptorsystem.api.repository.PostoDeTrabalhoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletePostoDeTrabalhoUseCaseTest {

    @Mock
    private PostoDeTrabalhoRepository postoDeTrabalhoRepository;
    @InjectMocks
    private DeletePostoDeTrabalhoUseCase deletePostoDeTrabalhoUseCase;

    @Test
    @DisplayName("Deve deletar um posto de trabalho com sucesso")
    void deletePosto_withValidId_shouldSucceed() {
        // Arrange
        UUID id = UUID.randomUUID();
        // Simula que o repositório encontra o ID
        when(postoDeTrabalhoRepository.existsById(id)).thenReturn(true);
        // Configura o mock para não fazer nada quando deleteById for chamado
        doNothing().when(postoDeTrabalhoRepository).deleteById(id);

        // Action & Assert
        // Verifica que nenhuma exceção é lançada
        assertDoesNotThrow(() -> {
            deletePostoDeTrabalhoUseCase.execute(id);
        });
        // Verifica que o método deleteById foi chamado exatamente uma vez
        verify(postoDeTrabalhoRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar posto de trabalho inexistente")
    void deletePosto_withInvalidId_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        // Simula que o repositório NÃO encontra o ID
        when(postoDeTrabalhoRepository.existsById(id)).thenReturn(false);

        // Action & Assert
        // Verifica que a exceção correta é lançada
        assertThrows(PostoDeTrabalhoNaoEncontradoException.class, () -> {
            deletePostoDeTrabalhoUseCase.execute(id);
        });
        // Verifica que o método deleteById NUNCA foi chamado
        verify(postoDeTrabalhoRepository, never()).deleteById(any());
    }
}
