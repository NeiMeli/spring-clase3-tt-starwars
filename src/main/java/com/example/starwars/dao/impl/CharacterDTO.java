package com.example.starwars.dao.impl;

public class CharacterDTO {
    private String name;

    public CharacterDTO(String name) {
        this.name = name;
    }

    public CharacterDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
