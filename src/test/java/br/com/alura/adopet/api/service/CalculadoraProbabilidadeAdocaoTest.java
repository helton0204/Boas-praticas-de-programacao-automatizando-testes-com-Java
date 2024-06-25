package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Probabilidade alta para gatos jovens com peso baixo")
    void probabilidadeAltaCenario1(){
        //teste do método calcular() da classe CalculadoraProbabilidadeAdocao para um gato de 4 anos e peso 4kg - retorno esperado ALTA
        //ARRANGE - etapa de carregamento de dados
        CadastroAbrigoDto cadastroAbrigoDto = new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br");
        CadastroPetDto cadastroPetDto = new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 4, "Cinza", 4.0f);
        Abrigo abrigo = new Abrigo(cadastroAbrigoDto);
        Pet pet = new Pet(cadastroPetDto, abrigo);
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();

        //ACT - etapa de execução do código a ser testado
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        //ASSERT - etapa de verificação dos resultados do teste
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidadeAdocao);
    }

    @Test
    @DisplayName("Probabilidade média para gatos idosos com peso baixo")
    void probabilidadeMediaCenario2(){
        //teste do método calcular() da classe CalculadoraProbabilidadeAdocao para um gato de 15 anos e peso 4kg - retorno esperado MEDIA
        //ARRANGE - etapa de carregamento de dados
        CadastroAbrigoDto cadastroAbrigoDto = new CadastroAbrigoDto("Abrigo feliz", "94999999999", "abrigofeliz@email.com.br");
        CadastroPetDto cadastroPetDto = new CadastroPetDto(TipoPet.GATO, "Miau", "Siames", 15, "Cinza", 4.0f);
        Abrigo abrigo = new Abrigo(cadastroAbrigoDto);
        Pet pet = new Pet(cadastroPetDto, abrigo);
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();

        //ACT - etapa de execução do código a ser testado
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        //ASSERT - etapa de verificação dos resultados do teste
        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidadeAdocao);
    }

}