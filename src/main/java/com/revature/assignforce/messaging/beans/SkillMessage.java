package com.revature.assignforce.messaging.beans;

public class SkillMessage {
    private String context;
    private int skillId;

    public SkillMessage() {
    }

    public SkillMessage(String context, int skillId) {
        this.context = context;
        this.skillId = skillId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
    
}
