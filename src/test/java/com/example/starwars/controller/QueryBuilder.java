package com.example.starwars.controller;

import com.example.starwars.dto.QueryDTO;
import net.minidev.json.JSONValue;

public class QueryBuilder {
    public static String buildQueryDTOJson(String query) {
        return JSONValue.toJSONString(new QueryDTO().setQuery(query));
    }
}
