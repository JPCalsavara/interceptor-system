package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.entity.FuncionarioEntity;
import com.interceptorsystem.api.exception.FuncionarioNaoEncontradoException;
import com.interceptorsystem.api.repository.FuncionarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetFuncionarioUseCaseTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private GetAllFuncionarioUseCase getAllFuncionarioUseCase;

    @InjectMocks
    private GetFuncionarioByIdUseCase getFuncionarioByIdUseCase;

    @Test
    @DisplayName("GetAll: Deve retornar uma lista de funcionários")
    void getAllFuncionario_shouldReturnList() throws Exception {
        // Arrange
        when(funcionarioRepository.findAll()).thenReturn(List.of(new FuncionarioEntity(), new FuncionarioEntity()));
        // Action
        List<FuncionarioEntity> result = getAllFuncionarioUseCase.execute();
        // Assert
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("GetById: Deve retornar um funcionário quando o ID for válido")
    void getFuncionarioById_withValidId_shouldReturnFuncionario() throws Exception {
        // Arrange
        UUID id = UUID.randomUUID();
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new FuncionarioEntity()));
        // Action
        FuncionarioEntity result = getFuncionarioByIdUseCase.execute(id);
        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("GetById: Deve lançar FuncionarioNaoEncontradoException quando o ID não existir")
    void getFuncionarioById_withInvalidId_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());
        // Action & Assert
        assertThrows(FuncionarioNaoEncontradoException.class, () -> {
            getFuncionarioByIdUseCase.execute(id);
        });
    }
}
