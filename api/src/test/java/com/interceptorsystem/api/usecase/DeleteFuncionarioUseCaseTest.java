package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.exception.FuncionarioNaoEncontradoException;
import com.interceptorsystem.api.repository.FuncionarioRepository;
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
public class DeleteFuncionarioUseCaseTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private DeleteFuncionarioUseCase deleteFuncionarioUseCase;

    @Test
    @DisplayName("Deve deletar um funcionário com sucesso quando o ID for válido")
    void deleteFuncionario_withValidId_shouldSucceed() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(funcionarioRepository.existsById(id)).thenReturn(true);
        doNothing().when(funcionarioRepository).deleteById(id);

        // Action & Assert
        assertDoesNotThrow(() -> {
            deleteFuncionarioUseCase.execute(id);
        });
        verify(funcionarioRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar FuncionarioNaoEncontradoException quando o ID não existir")
    void deleteFuncionario_withInvalidId_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(funcionarioRepository.existsById(id)).thenReturn(false);

        // Action & Assert
        assertThrows(FuncionarioNaoEncontradoException.class, () -> {
            deleteFuncionarioUseCase.execute(id);
        });
        verify(funcionarioRepository, never()).deleteById(any());
    }
}
