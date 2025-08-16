package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusCondominio;
import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.domain.vo.CNPJ;
import com.interceptorsystem.api.exception.CondominioJaExisteException;
import com.interceptorsystem.api.repository.CondominioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCondominioUseCaseTest {

    @Mock
    private CondominioRepository condominioRepository;

    @InjectMocks
    private CreateCondominioUseCase createCondominioUseCase;

    @Test
    @DisplayName("Deve criar um condomínio com sucesso com dados válidos")
    void createCondominio_withValidData_shouldSucceed() throws Exception {
        // Arrange
        var request = new CondominioRequestDTO(
                "Condomínio Central", "11.222.333/0001-44", StatusCondominio.ATIVO,
                "Rua Principal", "123", "Apto 101", "Centro", "São Paulo", "SP", "01000-000"
        );
        when(condominioRepository.findByCnpj(any(CNPJ.class))).thenReturn(Optional.empty());
        when(condominioRepository.save(any(CondominioEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        // Action
        CondominioEntity result = createCondominioUseCase.execute(request);

        // Assert
        assertNotNull(result);
        assertEquals("Condomínio Central", result.getNome());
        assertNotNull(result.getEndereco());
        assertEquals("Rua Principal", result.getEndereco().getLogradouro());
        verify(condominioRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar CondominioJaExisteException quando o CNPJ já existir")
    void createCondominio_withExistingCnpj_shouldThrowCondominioJaExisteException() {
        // Arrange
        var request = new CondominioRequestDTO(
                "Condomínio Repetido", "11.222.333/0001-44", StatusCondominio.ATIVO,
                "Rua Principal", "123", null, "Centro", "São Paulo", "SP", "01000-000"
        );
        when(condominioRepository.findByCnpj(any(CNPJ.class))).thenReturn(Optional.of(new CondominioEntity()));

        // Action & Assert
        assertThrows(CondominioJaExisteException.class, () -> {
            createCondominioUseCase.execute(request);
        });
        verify(condominioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar Exception para dados de VO inválidos (ex: CEP incorreto)")
    void createCondominio_withInvalidVoData_shouldThrowException() {
        // Arrange
        var requestWithInvalidCep = new CondominioRequestDTO(
                "Condomínio com Erro", "11.222.333/0001-44", StatusCondominio.ATIVO,
                "Rua Válida", "123", null, "Centro", "São Paulo", "SP", "CEP_INVALIDO"
        );

        // Action & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            createCondominioUseCase.execute(requestWithInvalidCep);
        });

        // Verifica se a causa do erro foi a validação do Value Object
        assertTrue(exception.getCause() instanceof IllegalArgumentException);
        assertTrue(exception.getMessage().contains("Dados inválidos fornecidos"));
        verify(condominioRepository, never()).save(any());
    }
}
