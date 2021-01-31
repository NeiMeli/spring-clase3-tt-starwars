package com.example.starwars.service;

import com.example.starwars.dao.StarWarsDAO;
import com.example.starwars.dao.impl.CharacterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class StarWarsServiceImplTest {

    @Autowired
    StarWarsServiceImpl service;
    @MockBean
    StarWarsDAO dao;

    @Test
    void findCharactersHappy() {
        when(dao.listByName(anyString())).thenReturn(List.of(new CharacterDTO("ch1"), new CharacterDTO("ch2"), new CharacterDTO("ch3")));
        assertThat(service.findCharacters("query")).isEqualTo("ch1, ch2, ch3");
    }

    @Test
    void findCharactersEmpty() {
        when(dao.listByName(anyString())).thenReturn(Collections.emptyList());
        assertThat(service.findCharacters("query")).isEmpty();
    }
}