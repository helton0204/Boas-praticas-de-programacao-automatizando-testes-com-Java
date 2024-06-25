package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc //essa anotação é para injetar o MockMvc
class AdocaoControllerTest {
    @Autowired
    private MockMvc requisicao; //MockMvc é uma classe que simula requisições para o backend
    @MockBean
    private AdocaoService adocaoservice;

    @Test
    @DisplayName("Teste para verificar se devolve erro 400 caso tenha erro na solicitação de adoção")
    void solicitacaoDeAdocaoComErros() throws Exception {
        //ARRANGE
        String json = "{}";

        //ACT
        var resposta = requisicao.perform(
          post("/adocoes").content(json).contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, resposta.getStatus());
    }

    @Test
    @DisplayName("Teste para verificar se devolve código 200 caso a solicitação de adoção seja bem sucedida")
    void solicitacaoDeAdocaoSemErros() throws Exception {
        //ARRANGE
        String json = """
                {
                    "idPet": 1,
                    "idTutor": 1,
                    "motivo": "Motivo qualquer"
                }
                """;

        //ACT
        var resposta = requisicao.perform(
                post("/adocoes").content(json).contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(200, resposta.getStatus());
    }

}