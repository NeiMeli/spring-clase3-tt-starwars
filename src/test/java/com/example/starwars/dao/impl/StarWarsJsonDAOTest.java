package com.example.starwars.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StarWarsJsonDAOTest {
    @Autowired
    StarWarsJsonDAO dao;

    @Test
    void listByName() {
        assertThat(dao.listByName("Darth").stream().map(CharacterDTO::getName)).containsExactly("Darth Vader", "Darth Maul");
        assertThat(dao.listByName("LUK").stream().map(CharacterDTO::getName)).containsExactly("Luke Skywalker");
        assertThat(dao.listByName("non-existant-character")).isEmpty();
    }
}