package com.example.crud.domain.users;

import java.util.List;

public class JsonResponse {
    private List<RequestUsers> results;

    public List<RequestUsers> getResults() {
        return results;
    }

    public void setResults(List<RequestUsers> results) {
        this.results = results;
    }
}
