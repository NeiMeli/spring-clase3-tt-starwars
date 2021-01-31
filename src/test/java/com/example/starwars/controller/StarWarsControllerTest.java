package com.example.starwars.controller;

import com.example.starwars.dto.ResponseDto;
import com.example.starwars.service.StarWarsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StarWarsControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StarWarsService service;

    @Autowired
    MockMvc mockMvc;

    private static final String PATH = "/starwars/character";

    @Test
    void findCharactersHappy() throws Exception {
        when(service.findCharacters(anyString())).thenReturn("characters");
        MvcResult result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON).content(QueryBuilder.buildQueryDTOJson("query")))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        ResponseDto response = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseDto.class);
        assertThat(response.getCharacters()).isEqualTo("characters");
    }

    @Test
    void findCharactersEmpty() throws Exception {
        when(service.findCharacters(anyString())).thenReturn("");
        MvcResult result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON).content(QueryBuilder.buildQueryDTOJson("query")))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        ResponseDto response = objectMapper.readValue(result.getResponse().getContentAsString(), ResponseDto.class);
        assertThat(response.getCharacters()).isEmpty();
    }
}