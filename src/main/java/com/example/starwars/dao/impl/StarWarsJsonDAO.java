package com.example.starwars.dao.impl;

import com.example.starwars.dao.StarWarsDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StarWarsJsonDAO implements StarWarsDAO {
    private final List<CharacterDTO> database;

    public StarWarsJsonDAO() throws Exception {
        File file = ResourceUtils.getFile("src/main/resources/database/starwars.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodes = objectMapper.readTree(file);
        database = new ArrayList<>();
        jsonNodes.forEach(jn -> database.add(new CharacterDTO(jn.get("name").textValue())));
    }

    @Override
    public List<CharacterDTO> listByName(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return database.stream()
                .filter(ch -> ch.getName().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
}
