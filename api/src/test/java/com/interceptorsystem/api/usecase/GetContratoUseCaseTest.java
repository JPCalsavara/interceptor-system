package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.exception.ContratoNaoEncontradoException;
import com.interceptorsystem.api.repository.ContratoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetContratoUseCaseTest {

    @Mock
    private ContratoRepository contratoRepository;

    // Injetando os dois UseCases que serão testados nesta classe
    @InjectMocks
    private GetAllContratoUseCase getAllContratoUseCase;

    @InjectMocks
    private GetContratoByIdUseCase getContratoByIdUseCase;

    //--- Testes para GetAllcontratoUseCase ---

    @Test
    @DisplayName("GetAll: Deve retornar uma lista de contrato quando existirem registros")
    void getAllcontrato_whenContractsExist_shouldReturnList() {
        // Arrange
        List<ContratoEntity> mockList = Arrays.asList(new ContratoEntity(), new ContratoEntity());
        when(contratoRepository.findAll()).thenReturn(mockList);

        // Action
        List<ContratoEntity> result = getAllContratoUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("GetAll: Deve retornar uma lista vazia quando não existirem contrato")
    void getAllcontrato_whenNoContractsExist_shouldReturnEmptyList() {
        // Arrange
        when(contratoRepository.findAll()).thenReturn(Collections.emptyList());

        // Action
        List<ContratoEntity> result = getAllContratoUseCase.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    //--- Testes para GetContratoByIdUseCase ---

    @Test
    @DisplayName("GetById: Deve retornar um contrato quando o ID for válido")
    void getContratoById_withValidId_shouldReturnContract() throws Exception {
        // Arrange
        UUID contratoId = UUID.randomUUID();
        ContratoEntity mockContrato = new ContratoEntity();
        mockContrato.setId(contratoId);

        when(contratoRepository.findById(contratoId)).thenReturn(Optional.of(mockContrato));

        // Action
        ContratoEntity result = getContratoByIdUseCase.execute(contratoId);

        // Assert
        assertNotNull(result);
        assertEquals(contratoId, result.getId());
    }

    @Test
    @DisplayName("GetById: Deve lançar ContratoNaoEncontradoException quando o ID não existir")
    void getContratoById_withInvalidId_shouldThrowNotFoundException() {
        // Arrange
        UUID invalidId = UUID.randomUUID();
        when(contratoRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Action & Assert
        assertThrows(ContratoNaoEncontradoException.class, () -> {
            getContratoByIdUseCase.execute(invalidId);
        });
    }
}
