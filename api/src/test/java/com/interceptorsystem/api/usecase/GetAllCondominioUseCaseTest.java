package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.repository.CondominioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllCondominioUseCaseTest {

    @Mock
    private CondominioRepository condominioRepository;

    @InjectMocks
    private GetAllCondominioUseCase getAllCondominioUseCase;

    @Test
    @DisplayName("Deve retornar uma lista de condomínios quando existirem registros")
    void getAllCondominios_whenCondominiosExist_shouldReturnList() {
        // Arrange (Preparação)
        // Cria uma lista de condomínios falsos para o teste
        List<CondominioEntity> mockList = Arrays.asList(
                new CondominioEntity(),
                new CondominioEntity()
        );

        // Configura o mock para retornar a lista quando o método findAll for chamado
        when(condominioRepository.findAll()).thenReturn(mockList);

        // Action (Execução)
        List<CondominioEntity> result = getAllCondominioUseCase.execute();

        // Assert (Verificação)
        assertNotNull(result);
        assertEquals(2, result.size()); // Verifica se a lista tem o tamanho esperado
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando não existirem condomínios")
    void getAllCondominios_whenNoCondominiosExist_shouldReturnEmptyList() {
        // Arrange (Preparação)
        // Configura o mock para retornar uma lista vazia
        when(condominioRepository.findAll()).thenReturn(Collections.emptyList());

        // Action (Execução)
        List<CondominioEntity> result = getAllCondominioUseCase.execute();

        // Assert (Verificação)
        assertNotNull(result);
        assertEquals(0, result.size()); // Verifica se a lista está vazia
    }
}
