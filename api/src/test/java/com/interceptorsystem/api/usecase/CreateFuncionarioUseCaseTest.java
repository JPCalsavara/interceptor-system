package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusFuncionario;
import com.interceptorsystem.api.domain.enums.TipoEscala;
import com.interceptorsystem.api.domain.enums.TipoFuncionario;
import com.interceptorsystem.api.dto.FuncionarioRequestDTO;
import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.domain.vo.CPF;
import com.interceptorsystem.api.exception.FuncionarioJaExisteException;
import com.interceptorsystem.api.repository.FuncionarioRepository;
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
public class CreateFuncionarioUseCaseTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private CreateFuncionarioUseCase createFuncionarioUseCase;

    @Test
    @DisplayName("Deve criar um funcionário com sucesso quando o CPF for único")
    void createFuncionario_withUniqueCpf_shouldSucceed() throws Exception {
        // Arrange
        var requestDTO = new FuncionarioRequestDTO("João Silva", "12345678901", "11987654321", StatusFuncionario.ATIVO, TipoEscala.DOZE_POR_TRINTA_SEIS, TipoFuncionario.CLT);
        when(funcionarioRepository.findByCpf(any(CPF.class))).thenReturn(Optional.empty());
        when(funcionarioRepository.save(any(FuncionarioEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Action
        FuncionarioEntity result = createFuncionarioUseCase.execute(requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("João Silva", result.getNome());
        verify(funcionarioRepository, times(1)).save(any(FuncionarioEntity.class));
    }

    @Test
    @DisplayName("Deve lançar FuncionarioJaExisteException quando o CPF já existir")
    void createFuncionario_withExistingCpf_shouldThrowException() {
        // Arrange
        var requestDTO = new FuncionarioRequestDTO("João Silva", "12345678901", "11987654321", StatusFuncionario.ATIVO, TipoEscala.DOZE_POR_TRINTA_SEIS, TipoFuncionario.CLT);
        when(funcionarioRepository.findByCpf(any(CPF.class))).thenReturn(Optional.of(new FuncionarioEntity()));

        // Action & Assert
        assertThrows(FuncionarioJaExisteException.class, () -> {
            createFuncionarioUseCase.execute(requestDTO);
        });
        verify(funcionarioRepository, never()).save(any());
    }
}
