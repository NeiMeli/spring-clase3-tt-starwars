package com.example.starwars.dao;

import com.example.starwars.dao.impl.CharacterDTO;

import java.util.List;

public interface StarWarsDAO {
    List<CharacterDTO> listByName(String query);
}
