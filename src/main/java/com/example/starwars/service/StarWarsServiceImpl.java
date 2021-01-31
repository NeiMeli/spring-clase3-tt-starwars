package com.example.starwars.service;

import com.example.starwars.dao.StarWarsDAO;
import com.example.starwars.dao.impl.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarWarsServiceImpl implements StarWarsService {
    @Autowired
    StarWarsDAO dao;

    @Override
    public String findCharacters(String query) {
        List<String> characters = dao.listByName(query)
                .stream()
                .map(CharacterDTO::getName)
                .collect(Collectors.toList());
        return String.join(", ", characters);
    }
}
