package com.revature.assignforce.beans;

public class SkillMessage {
    private String context;
    private Integer id;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SkillMessage() {

    }

    public SkillMessage(String context, int id) {
        this.context = context;
        this.id = id;
    }
}
