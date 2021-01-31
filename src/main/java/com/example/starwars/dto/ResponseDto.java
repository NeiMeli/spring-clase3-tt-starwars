package com.example.starwars.dto;

public class ResponseDto {
    private String characters;

    public String getCharacters() {
        return characters;
    }

    public ResponseDto setCharacters(String characters) {
        this.characters = characters;
        return this;
    }
}
