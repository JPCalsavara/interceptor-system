package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusContrato;
import com.interceptorsystem.api.dto.ContratoRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.entity.ContratoEntity;
import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
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
public class CreateContratoUseCaseTest {

    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private CondominioRepository condominioRepository;

    @InjectMocks
    private CreateContratoUseCase createContratoUseCase;

    @Test
    @DisplayName("Deve criar um contrato com sucesso quando o condomínio existir")
    void createContrato_whenCondominioExists_shouldSucceed() throws Exception {
        // Arrange (Preparação)
        // 1. Prepara os dados de entrada
        UUID condominioId = UUID.randomUUID();
        var requestDTO = new ContratoRequestDTO(
                "Contrato de Limpeza",
                new BigDecimal("2500.00"), // valorTotal
                new BigDecimal("80.00"),   // valorDiaria (argumento adicionado)
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                StatusContrato.PAGO,
                condominioId
        );

        // 2. Cria um mock da entidade de condomínio que será "encontrada" no banco
        var mockCondominio = new CondominioEntity();
        mockCondominio.setId(condominioId);

        // 3. Configura os mocks
        // Quando o repositório de condomínio for chamado, ele deve encontrar o nosso mock
        when(condominioRepository.findById(condominioId)).thenReturn(Optional.of(mockCondominio));
        // Quando o repositório de contrato for salvar, ele deve retornar a entidade que foi passada
        when(contratoRepository.save(any(ContratoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Action (Execução)
        ContratoEntity result = createContratoUseCase.execute(requestDTO);

        // Assert (Verificação)
        assertNotNull(result);
        assertEquals("Contrato de Limpeza", result.getDescricao());
        assertEquals(condominioId, result.getCondominio().getId()); // Verifica se o contrato foi associado ao condomínio correto
        verify(contratoRepository, times(1)).save(any(ContratoEntity.class)); // Garante que o método save foi chamado
    }

    @Test
    @DisplayName("Deve lançar CondominioNaoEncontradoException quando o condomínio não existir")
    void createContrato_whenCondominioDoesNotExist_shouldThrowException() {
        // Arrange (Preparação)
        UUID invalidCondominioId = UUID.randomUUID();
        var requestDTO = new ContratoRequestDTO(
                "Contrato Inválido",
                BigDecimal.ZERO, // valorTotal
                BigDecimal.ZERO, // valorDiaria (argumento adicionado)
                LocalDate.now(),
                LocalDate.now(),
                StatusContrato.PENDENTE,
                invalidCondominioId
        );

        // Configura o mock para retornar um Optional vazio, simulando que o condomínio não foi encontrado
        when(condominioRepository.findById(invalidCondominioId)).thenReturn(Optional.empty());

        // Action & Assert (Execução e Verificação)
        // Verifica se a exceção correta é lançada
        assertThrows(CondominioNaoEncontradoException.class, () -> {
            createContratoUseCase.execute(requestDTO);
        });

        // Garante que o método save do contrato NUNCA foi chamado
        verify(contratoRepository, never()).save(any());
    }
}
