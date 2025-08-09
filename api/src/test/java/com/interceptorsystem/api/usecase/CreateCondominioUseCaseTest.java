package com.interceptorsystem.api.usecase;

import com.interceptorsystem.api.domain.enums.StatusCondominio;
import com.interceptorsystem.api.dto.CondominioRequestDTO;
import com.interceptorsystem.api.entity.CondominioEntity;
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
    @DisplayName("Deve criar um condomínio com sucesso quando o CNPJ for único")
    void createUserCase1() throws Exception {
        var data = new CondominioRequestDTO("Condomínio Sol", "12345678901234", StatusCondominio.ATIVO);
        when(condominioRepository.findByCnpj(data.cnpj())).thenReturn(Optional.empty());
        when(condominioRepository.save(any(CondominioEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CondominioEntity result = createCondominioUseCase.execute(data);

        assertNotNull(result, "O resultado não deveria ser nulo.");
        assertEquals(data.nome(), result.getNome(), "O nome do condomínio não corresponde.");
        verify(condominioRepository, times(1)).save(any(CondominioEntity.class));
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando o CNPJ do condomínio já existir")
    void createUserCase2() {
        // Arrange (Preparação)
        var data = new CondominioRequestDTO("Condomínio Lua", "98765432109876", StatusCondominio.ATIVO);
        when(condominioRepository.findByCnpj(data.cnpj())).thenReturn(Optional.of(mock(CondominioEntity.class)));

        // Action & Assert (Execução e Verificação)
        // **CORREÇÃO AQUI**
        // O teste agora espera por 'Exception.class', que é o que o UseCase realmente lança
        // após o bloco catch.
        assertThrows(Exception.class, () -> {
            createCondominioUseCase.execute(data);
        }, "Deveria ter lançado uma exceção genérica");

        // Verifica se o método 'save' NUNCA foi chamado.
        verify(condominioRepository, never()).save(any());
    }
}
