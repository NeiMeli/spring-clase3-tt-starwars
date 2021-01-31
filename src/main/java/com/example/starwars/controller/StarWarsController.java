package com.example.starwars.controller;

import com.example.starwars.dto.QueryDTO;
import com.example.starwars.dto.ResponseDto;
import com.example.starwars.service.StarWarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/starwars")
public class StarWarsController {
    @Autowired
    StarWarsService service;

    @PostMapping("/character")
    @ResponseBody
    public ResponseDto findCharacters(@RequestBody QueryDTO query) {
        String characters = service.findCharacters(query.getQuery());
        return new ResponseDto().setCharacters(characters);
    }
}
