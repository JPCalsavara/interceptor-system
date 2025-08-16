package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusCondominio;
import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
import com.interceptorsystem.api.exception.CondominioNaoEncontradoException;
import com.interceptorsystem.api.repository.CondominioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateCondominioUseCaseTest {

    @Mock
    private CondominioRepository condominioRepository;

    @InjectMocks
    private UpdateCondominioUseCase updateCondominioUseCase; // Supondo que você tenha um UpdateCondominioUseCase

    @Test
    @DisplayName("Deve atualizar um condomínio com sucesso")
    void updateCondominio_withValidData_shouldSucceed() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        var request = new CondominioRequestDTO(
                "Nome Atualizado", "11.222.333/0001-44", StatusCondominio.INATIVO,
                "Rua Nova", "456", null, "Bairro Novo", "Rio de Janeiro", "RJ", "20000-000"
        );
        when(condominioRepository.findById(id)).thenReturn(Optional.of(new CondominioEntity()));
        when(condominioRepository.save(any(CondominioEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        // Action
        CondominioEntity result = updateCondominioUseCase.execute(id, request);

        // Assert
        assertNotNull(result);
        assertEquals("Nome Atualizado", result.getNome());
        assertEquals(StatusCondominio.INATIVO, result.getStatus());
        assertEquals("Rua Nova", result.getEndereco().getLogradouro());
        verify(condominioRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar CondominioNaoEncontradoException ao tentar atualizar condomínio inexistente")
    void updateCondominio_withInvalidId_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        var request = new CondominioRequestDTO("Nome", "123", StatusCondominio.ATIVO, "Rua", "1", null, "Bairro", "Cidade", "UF", "12345-678");
        when(condominioRepository.findById(id)).thenReturn(Optional.empty());

        // Action & Assert
        assertThrows(CondominioNaoEncontradoException.class, () -> {
            updateCondominioUseCase.execute(id, request);
        });
        verify(condominioRepository, never()).save(any());
    }
}
