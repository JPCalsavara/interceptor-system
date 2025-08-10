package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
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
public class DeleteCondominioUseCaseTest {

    @Mock
    private CondominioRepository condominioRepository;

    @InjectMocks
    private DeleteCondominioUseCase deleteCondominioUseCase;

    @Test
    @DisplayName("Deve deletar um condomínio com sucesso quando o ID for válido")
    void deleteCondominio_withValidId_shouldCompleteSuccessfully() {
        // Arrange (Preparação)
        UUID condominioId = UUID.randomUUID();

        // Configura o mock para simular que o condomínio existe.
        when(condominioRepository.existsById(condominioId)).thenReturn(true);
        // Configura o mock para não fazer nada quando deleteById for chamado (comportamento padrão, mas bom para clareza).
        doNothing().when(condominioRepository).deleteById(condominioId);

        // Action & Assert (Execução e Verificação)
        // Verifica que nenhuma exceção é lançada ao executar o método.
        assertDoesNotThrow(() -> {
            deleteCondominioUseCase.execute(condominioId);
        });

        // Verifica se o método deleteById foi chamado exatamente uma vez com o ID correto.
        verify(condominioRepository, times(1)).deleteById(condominioId);
    }

    @Test
    @DisplayName("Deve lançar CondominioNaoEncontradoException quando o ID não existir")
    void deleteCondominio_withInvalidId_shouldThrowNotFoundException() {
        // Arrange (Preparação)
        UUID invalidId = UUID.randomUUID();

        // Configura o mock para simular que o condomínio NÃO existe.
        when(condominioRepository.existsById(invalidId)).thenReturn(false);

        // Action & Assert (Execução e Verificação)
        // Verifica se a exceção correta é lançada.
        assertThrows(CondominioNaoEncontradoException.class, () -> {
            deleteCondominioUseCase.execute(invalidId);
        });

        // Garante que o método deleteById nunca foi chamado, pois a execução parou no erro.
        verify(condominioRepository, never()).deleteById(any(UUID.class));
    }
}
