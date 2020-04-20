package com.example.myfirstapplication;

import java.util.List;

public class PokemonRestResponse {

    private Integer count;
    private String next;
    private String previous;
    private List<Pokemon> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Pokemon> getResults() {
        return results;
    }
}
