package com.revature.assignforce.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "Curriculum")
public class Curriculum {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "curr")
	@SequenceGenerator(name = "curr", sequenceName = "curr_seq", allocationSize = 1)
	@Column(name = "CURR_ID")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "isActive")
	private Boolean isActive;

	@Column(name = "isCore")
	private Boolean isCore;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "CURR_SKILLS")
	private Set<SkillIdHolder> skills;

	public Curriculum() {
		super();
	}

	public Curriculum(int id, String name, Boolean isActive, Boolean isCore, Set<SkillIdHolder> skills) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.isCore = isCore;
		this.skills = skills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsCore() {
		return isCore;
	}

	public void setIsCore(Boolean isCore) {
		this.isCore = isCore;
	}

	public Set<SkillIdHolder> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillIdHolder> skills) {
		this.skills = skills;
	}

}
