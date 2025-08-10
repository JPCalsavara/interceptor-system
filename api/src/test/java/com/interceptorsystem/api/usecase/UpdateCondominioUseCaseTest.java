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
    private UpdateCondominioUseCase updateCondominioUseCase;

    @Test
    @DisplayName("Deve atualizar um condomínio com sucesso quando o ID for válido")
    void updateCondominio_withValidId_shouldReturnUpdatedCondominio() throws Exception {
        // Arrange (Preparação)
        // 1. Define os dados de entrada
        UUID condominioId = UUID.randomUUID();
        var updateData = new CondominioRequestDTO("Nome Atualizado", "12345678901234", StatusCondominio.INATIVO);

        // 2. Cria a entidade como ela existe no banco ANTES da atualização
        var existingCondominio = new CondominioEntity("Nome Antigo", "12345678901234", StatusCondominio.ATIVO);
        existingCondominio.setId(condominioId); // Define o ID para a entidade existente

        // 3. Configura o mock para encontrar o condomínio pelo ID
        when(condominioRepository.findById(condominioId)).thenReturn(Optional.of(existingCondominio));

        // 4. Configura o mock para simular a operação de salvar, retornando a entidade salva
        when(condominioRepository.save(any(CondominioEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Action (Execução)
        CondominioEntity updatedCondominio = updateCondominioUseCase.execute(condominioId, updateData);

        // Assert (Verificação)
        assertNotNull(updatedCondominio);
        assertEquals(condominioId, updatedCondominio.getId());
        assertEquals("Nome Atualizado", updatedCondominio.getNome()); // Verifica se o nome foi atualizado
        assertEquals(StatusCondominio.INATIVO, updatedCondominio.getStatus()); // Verifica se o status foi atualizado
        assertEquals("12345678901234", updatedCondominio.getCnpj()); // Verifica se o CNPJ permaneceu o mesmo

        verify(condominioRepository, times(1)).findById(condominioId);
        verify(condominioRepository, times(1)).save(any(CondominioEntity.class));
    }

    @Test
    @DisplayName("Deve lançar CondominioNaoEncontradoException quando o ID não existir")
    void updateCondominio_withInvalidId_shouldThrowNotFoundException() {
        // Arrange (Preparação)
        UUID invalidId = UUID.randomUUID();
        var updateData = new CondominioRequestDTO("Qualquer Nome", "12345678901234", StatusCondominio.ATIVO);

        // Configura o mock para retornar um Optional vazio, simulando que o condomínio não foi encontrado
        when(condominioRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Action & Assert (Execução e Verificação)
        // Verifica se a exceção correta é lançada
        assertThrows(CondominioNaoEncontradoException.class, () -> {
            updateCondominioUseCase.execute(invalidId, updateData);
        });

        // Garante que o método save nunca foi chamado, pois a execução parou no erro
        verify(condominioRepository, never()).save(any(CondominioEntity.class));
    }
}
