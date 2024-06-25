package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetComAdocaoEmAndamentoTest {
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoAdocaoDto dto;
    @Mock
    StatusAdocao statusAdocao;
    @InjectMocks //Cria uma instância da classe ValidacaoPetComAdocaoEmAndamento e injeta as dependências mockadas nos campos correspondentes
    private ValidacaoPetComAdocaoEmAndamento validacaoPetComAdocaoEmAndamento;

    @Test
    @DisplayName("Testando o cenário em que o pet não tem adoção em andamento")
    void petSemAdocaoEmAndamento(){
        //ARRANGE - etapa de carregamento de dados
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), statusAdocao.AGUARDANDO_AVALIACAO)).willReturn(false);

        //ACT + ASSERT
        Assertions.assertDoesNotThrow(() -> validacaoPetComAdocaoEmAndamento.validar(dto));
    }

    @Test
    @DisplayName("Testando o cenário em que o pet tem adoção em andamento")
    void petComAdocaoEmAndamento(){
        //ARRANGE - etapa de carregamento de dados
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), statusAdocao.AGUARDANDO_AVALIACAO)).willReturn(true);

        //ACT + ASSERT
        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetComAdocaoEmAndamento.validar(dto));
    }

}