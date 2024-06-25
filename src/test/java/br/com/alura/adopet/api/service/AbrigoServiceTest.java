package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {
    @Mock
    private AbrigoRepository abrigoRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private Abrigo abrigo;
    @InjectMocks //Cria uma instância da classe AbrigoService e injeta as dependências mockadas nos campos correspondentes
    private AbrigoService abrigoService;

    @Test
    @DisplayName("Teste para chamar lista de todos os abrigos")
    void deveriaChamarListaDeTodosOsAbrigos(){
        //ARRANGE
        Abrigo abrigo1 = new Abrigo(); // Configure as propriedades conforme necessário
        Abrigo abrigo2 = new Abrigo(); // Configure as propriedades conforme necessário
        List<Abrigo> listaAbrigos = List.of(abrigo1, abrigo2);

        AbrigoDto abrigoDto1 = new AbrigoDto(abrigo1);
        AbrigoDto abrigoDto2 = new AbrigoDto(abrigo2);
        List<AbrigoDto> listaAbrigosDtoEsperada = List.of(abrigoDto1, abrigoDto2);

        BDDMockito.given(abrigoRepository.findAll()).willReturn(listaAbrigos);

        //ACT
        List<AbrigoDto> resultadoLista = abrigoService.listar();

        //ASSERT
        BDDMockito.then(abrigoRepository).should().findAll();
        Assertions.assertEquals(listaAbrigosDtoEsperada, resultadoLista);
    }

    @Test
    @DisplayName("Dado o nome do abrigo chamar a lista de todos os pets desse abrigo")
    void deveriaChamarListaDePetsDoAbrigoAtravesDoNome() {
        //Arrange
        String nome = "Miau";
        BDDMockito.given(abrigoRepository.findByNome(nome)).willReturn(Optional.of(abrigo));

        //Act
        abrigoService.listarPetsDoAbrigo(nome);

        //Assert
        BDDMockito.then(petRepository).should().findByAbrigo(abrigo);
    }

    @Test
    @DisplayName("Dado o id do abrigo chamar lista de todos os pets desse abrigo")
    void deveriaChamarListaDePetsDoAbrigoAtravesDoId() {
        //Arrange
        String id = "1";
        BDDMockito.given(abrigoRepository.findById(Long.parseLong(id))).willReturn(Optional.of(abrigo));

        //Act
        abrigoService.listarPetsDoAbrigo(id);

        //Assert
        BDDMockito.then(petRepository).should().findByAbrigo(abrigo);
    }
}