package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusFuncionario;
import com.interceptorsystem.api.domain.enums.TipoEscala;
import com.interceptorsystem.api.domain.enums.TipoFuncionario;
import com.interceptorsystem.api.dto.FuncionarioRequestDTO;
import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.exception.FuncionarioNaoEncontradoException;
import com.interceptorsystem.api.repository.FuncionarioRepository;
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
public class UpdateFuncionarioUseCaseTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private UpdateFuncionarioUseCase updateFuncionarioUseCase;

    @Test
    @DisplayName("Deve atualizar um funcionário com sucesso quando o ID for válido")
    void updateFuncionario_withValidId_shouldSucceed() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        var requestDTO = new FuncionarioRequestDTO("Nome Atualizado", "12345678901", "11987654321", StatusFuncionario.DEMITIDO, TipoEscala.SEMANAL_COMERCIAL, TipoFuncionario.TERCEIRIZADO);
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new FuncionarioEntity()));
        when(funcionarioRepository.save(any(FuncionarioEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Action
        FuncionarioEntity result = updateFuncionarioUseCase.execute(id, requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Nome Atualizado", result.getNome());
        assertEquals(StatusFuncionario.DEMITIDO, result.getStatusFuncionario());
        verify(funcionarioRepository, times(1)).save(any(FuncionarioEntity.class));
    }

    @Test
    @DisplayName("Deve lançar FuncionarioNaoEncontradoException quando o ID não existir")
    void updateFuncionario_withInvalidId_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        var requestDTO = new FuncionarioRequestDTO("Nome", "123", "123", StatusFuncionario.ATIVO, TipoEscala.DOZE_POR_TRINTA_SEIS, TipoFuncionario.TERCEIRIZADO);
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        // Action & Assert
        assertThrows(FuncionarioNaoEncontradoException.class, () -> {
            updateFuncionarioUseCase.execute(id, requestDTO);
        });
        verify(funcionarioRepository, never()).save(any());
    }
}
