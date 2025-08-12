package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.exception.ContratoNaoEncontradoException;
import com.interceptorsystem.api.repository.ContratoRepository;
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
public class DeleteContratoUseCaseTest {

    @Mock
    private ContratoRepository contratoRepository;

    @InjectMocks
    private DeleteContratoUseCase deleteContratoUseCase;

    @Test
    @DisplayName("Deve deletar um contrato com sucesso quando o ID for válido")
    void deleteContrato_withValidId_shouldCompleteSuccessfully() {
        // Arrange (Preparação)
        UUID contratoId = UUID.randomUUID();

        // Configura o mock para simular que o contrato existe.
        when(contratoRepository.existsById(contratoId)).thenReturn(true);
        // Configura o mock para não fazer nada quando deleteById for chamado (comportamento padrão).
        doNothing().when(contratoRepository).deleteById(contratoId);

        // Action & Assert (Execução e Verificação)
        // Verifica que nenhuma exceção é lançada ao executar o método.
        assertDoesNotThrow(() -> {
            deleteContratoUseCase.execute(contratoId);
        });

        // Verifica se o método deleteById foi chamado exatamente uma vez com o ID correto.
        verify(contratoRepository, times(1)).deleteById(contratoId);
    }

    @Test
    @DisplayName("Deve lançar ContratoNaoEncontradoException quando o ID não existir")
    void deleteContrato_withInvalidId_shouldThrowNotFoundException() {
        // Arrange (Preparação)
        UUID invalidId = UUID.randomUUID();

        // Configura o mock para simular que o contrato NÃO existe.
        when(contratoRepository.existsById(invalidId)).thenReturn(false);

        // Action & Assert (Execução e Verificação)
        // Verifica se a exceção correta é lançada.
        assertThrows(ContratoNaoEncontradoException.class, () -> {
            deleteContratoUseCase.execute(invalidId);
        });

        // Garante que o método deleteById nunca foi chamado.
        verify(contratoRepository, never()).deleteById(any(UUID.class));
    }
}
