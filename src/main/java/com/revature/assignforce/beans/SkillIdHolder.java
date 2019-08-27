package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "SKILLIDHOLDER")
public class SkillIdHolder {
	
	@Id
	@Column(name="SKILL_ID")
	private int id;

	public SkillIdHolder() {
		super();
	}

	public SkillIdHolder(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
