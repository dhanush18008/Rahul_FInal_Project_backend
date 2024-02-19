package com.example.emp.model.Dto;

import com.example.emp.controller.Controller;

public class SkillRequest {
    private Integer id;
    private String skill;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setAction(Controller.SkillAction action) {
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public Controller.SkillAction getAction() {
        return action;
    }

    public SkillRequest() {
    }

    private Controller.SkillAction action;

    public SkillRequest(Integer employeeId, String skill, Controller.SkillAction action) {
        this.id = employeeId;
        this.skill = skill;
        this.action = action;
    }
}
