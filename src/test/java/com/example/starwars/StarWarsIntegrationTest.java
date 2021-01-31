package com.example.starwars;

import com.example.starwars.controller.QueryBuilder;
import com.example.starwars.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StarWarsIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private static final String PATH = "/starwars/character";

    @Test
    void findCharactersHappy() throws Exception {
        MvcResult result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON).content(QueryBuilder.buildQueryDTOJson("Dart")))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        ResponseDto response = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseDto.class);
        assertThat(response.getCharacters()).isEqualTo("Darth Vader, Darth Maul");

        MvcResult result2 = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON).content(QueryBuilder.buildQueryDTOJson("Luk")))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        ResponseDto response2 = objectMapper.readValue(result2.getResponse().getContentAsString(), ResponseDto.class);
        assertThat(response2.getCharacters()).isEqualTo("Luke Skywalker");
    }

    @Test
    void findCharactersEmpty() throws Exception {
        MvcResult result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON).content(QueryBuilder.buildQueryDTOJson("no-character-name")))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        ResponseDto response = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseDto.class);
        assertThat(response.getCharacters()).isEmpty();
    }
}
