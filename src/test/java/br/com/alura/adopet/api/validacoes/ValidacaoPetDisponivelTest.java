package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
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
class ValidacaoPetDisponivelTest {
    @Mock
    private Pet pet;
    @Mock
    private PetRepository petRepository;
    @Mock
    private SolicitacaoAdocaoDto dto;
    @InjectMocks //Cria uma instância da classe ValidacaoPetDisponivel e injeta as dependências mockadas nos campos correspondentes
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    @Test
    @DisplayName("Deve permitir que o pet seja adotado")
    void permitirSolicitacaoDeAdocaoPet(){
        //Este teste verifica se o método validar() permite que uma solicitação de adoção para um pet seja feita quando o pet não estiver adotado.
        //ARRANGE - etapa de carregamento de dados
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet); //aqui está definido o comportamento esperado dos mocks
        BDDMockito.given(pet.getAdotado()).willReturn(false); ////Os mocks são úteis quando você deseja fornecer respostas predefinidas para os métodos chamados durante os testes.

        //ACT + ASSERT
        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto));
    }

    @Test
    @DisplayName("Não deve permitir que o pet seja adotado")
    void naoPermitirSolicitacaoDeAdocaoPet(){
        //Este teste verifica se o método validar() lança uma excessão quando o pet já foi adotado.
        //ARRANGE - etapa de carregamento de dados
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        //ACT + ASSERT
        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetDisponivel.validar(dto));
    }

}