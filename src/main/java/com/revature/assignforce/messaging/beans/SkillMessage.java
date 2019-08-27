package com.revature.assignforce.messaging.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillMessage {
    private String context;
    @JsonProperty(value="skillId")
    private int id;

    public SkillMessage() {
    }

    public SkillMessage(String context, int skillId) {
        this.context = context;
        this.id = skillId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
