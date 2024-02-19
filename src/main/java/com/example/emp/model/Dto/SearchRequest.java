package com.example.emp.model.Dto;

import com.example.emp.controller.Controller;

import java.util.List;

public class SearchRequest {
    private List<String> skills;
    private Controller.SearchType searchType;

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setSearchType(Controller.SearchType searchType) {
        this.searchType = searchType;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Controller.SearchType getSearchType() {
        return searchType;
    }

    public SearchRequest() {
    }

    public SearchRequest(List<String> skills, Controller.SearchType searchType) {
        this.skills = skills;
        this.searchType = searchType;
    }
}
