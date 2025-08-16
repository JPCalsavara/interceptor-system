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
    @DisplayName("Deve atualizar um funcionário com sucesso com dados válidos")
    void updateFuncionario_withValidData_shouldSucceed() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        var request = new FuncionarioRequestDTO(
                "Carlos Andrade Silva", "111.222.333-44", "(19) 91111-2222",
                StatusFuncionario.AFASTADO, // <-- Valor do enum atualizado
                TipoEscala.SEMANAL_COMERCIAL, TipoFuncionario.TERCEIRIZADO,
                "Rua dos Jasmins", "200", "Casa", "Cidade Jardim", "Americana", "SP", "13400-000"
        );
        // Simula que o funcionário a ser atualizado foi encontrado no banco
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new FuncionarioEntity()));
        // Simula a ação de salvar, retornando a entidade que foi passada para o método
        when(funcionarioRepository.save(any(FuncionarioEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        // Action
        FuncionarioEntity result = updateFuncionarioUseCase.execute(id, request);

        // Assert
        assertNotNull(result);
        assertEquals("Carlos Andrade Silva", result.getNome());
        assertEquals(StatusFuncionario.AFASTADO, result.getStatusFuncionario()); // <-- Verificação atualizada
        assertEquals("19911112222", result.getCelular().getValor()); // Verifica o valor limpo
        verify(funcionarioRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar FuncionarioNaoEncontradoException ao tentar atualizar funcionário inexistente")
    void updateFuncionario_withInvalidId_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        var request = new FuncionarioRequestDTO("Nome", "123", "123", StatusFuncionario.ATIVO, TipoEscala.DOZE_POR_TRINTA_SEIS, TipoFuncionario.CLT, "Rua", "1", null, "Bairro", "Cidade", "UF", "12345-678");
        // Simula que o funcionário não foi encontrado
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        // Action & Assert
        assertThrows(FuncionarioNaoEncontradoException.class, () -> {
            updateFuncionarioUseCase.execute(id, request);
        });
        verify(funcionarioRepository, never()).save(any());
    }
}
