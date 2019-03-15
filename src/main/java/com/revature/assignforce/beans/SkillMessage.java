package com.revature.assignforce.beans;

public class SkillMessage {
    private String context;
    private Integer skillId;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public SkillMessage() {

    }

    public SkillMessage(String context, int skillId) {
        this.context = context;
        this.skillId = skillId;
    }
}
