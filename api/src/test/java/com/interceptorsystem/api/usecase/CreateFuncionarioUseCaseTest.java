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
    @DisplayName("Deve criar um funcionário com sucesso com dados válidos")
    void createFuncionario_withValidData_shouldSucceed() throws Exception {
        // Arrange
        var request = new FuncionarioRequestDTO(
                "Carlos Andrade", "111.222.333-44", "(19) 99876-5432",
                StatusFuncionario.ATIVO, TipoEscala.DOZE_POR_TRINTA_SEIS, TipoFuncionario.CLT,
                "Avenida Brasil", "1500", null, "Jardim Bela Vista", "Campinas", "SP", "13000-000"
        );
        when(funcionarioRepository.findByCpf(any(CPF.class))).thenReturn(Optional.empty());
        when(funcionarioRepository.save(any(FuncionarioEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        // Action
        FuncionarioEntity result = createFuncionarioUseCase.execute(request);

        // Assert
        assertNotNull(result);
        assertEquals("Carlos Andrade", result.getNome());
        assertNotNull(result.getEndereco());
        assertEquals("Avenida Brasil", result.getEndereco().getLogradouro());
        verify(funcionarioRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar FuncionarioJaExisteException quando o CPF já existir")
    void createFuncionario_withExistingCpf_shouldThrowException() {
        // Arrange
        var request = new FuncionarioRequestDTO(
                "Carlos Andrade", "111.222.333-44", "(19) 99876-5432",
                StatusFuncionario.ATIVO, TipoEscala.DOZE_POR_TRINTA_SEIS, TipoFuncionario.CLT,
                "Avenida Brasil", "1500", null, "Jardim Bela Vista", "Campinas", "SP", "13000-000"
        );
        when(funcionarioRepository.findByCpf(any(CPF.class))).thenReturn(Optional.of(new FuncionarioEntity()));

        // Action & Assert
        assertThrows(FuncionarioJaExisteException.class, () -> {
            createFuncionarioUseCase.execute(request);
        });
        verify(funcionarioRepository, never()).save(any());
    }
}
