package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusContrato;
import com.interceptorsystem.api.dto.ContratoRequestDTO;
import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.exception.ContratoNaoEncontradoException;
import com.interceptorsystem.api.repository.ContratoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateContratoUseCaseTest {

    @Mock
    private ContratoRepository contratoRepository;

    @InjectMocks
    private UpdateContratoUseCase updateContratoUseCase;

    @Test
    @DisplayName("Deve atualizar um contrato com sucesso quando o ID for válido")
    void updateContrato_withValidId_shouldReturnUpdatedContract() throws Exception {
        // Arrange
        UUID contratoId = UUID.randomUUID();
        var updateData = new ContratoRequestDTO(
                "Descrição Atualizada",
                new BigDecimal("1200.00"),
                new BigDecimal("40.00"),
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                StatusContrato.INATIVO,
                null // O ID do condomínio não é usado na atualização do contrato
        );

        var existingContrato = new ContratoEntity();
        existingContrato.setId(contratoId);

        when(contratoRepository.findById(contratoId)).thenReturn(Optional.of(existingContrato));
        when(contratoRepository.save(any(ContratoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Action
        ContratoEntity result = updateContratoUseCase.execute(contratoId, updateData);

        // Assert
        assertNotNull(result);
        assertEquals(contratoId, result.getId());
        assertEquals("Descrição Atualizada", result.getDescricao());
        assertEquals(StatusContrato.INATIVO, result.getStatus());
        verify(contratoRepository, times(1)).save(any(ContratoEntity.class));
    }

    @Test
    @DisplayName("Deve lançar ContratoNaoEncontradoException quando o ID não existir")
    void updateContrato_withInvalidId_shouldThrowNotFoundException() {
        // Arrange
        UUID invalidId = UUID.randomUUID();
        var updateData = new ContratoRequestDTO("Qualquer", BigDecimal.ZERO, BigDecimal.ZERO, LocalDate.now(), LocalDate.now(), StatusContrato.PAGO, null);

        when(contratoRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Action & Assert
        // O teste espera por ContratoNaoEncontradoException, que é o que o UseCase lança primeiro.
        assertThrows(ContratoNaoEncontradoException.class, () -> {
            updateContratoUseCase.execute(invalidId, updateData);
        });

        // Garante que o método save nunca foi chamado, pois a execução parou no erro.
        verify(contratoRepository, never()).save(any());
    }
}
