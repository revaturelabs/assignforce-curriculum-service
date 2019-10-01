package com.revature.assignforce.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@NotEmpty(message = "A curriculum must have a name.")
	@NotNull(message = "A curriculum must have a name.")
	@Pattern(regexp = "[a-zA-Z1-9]*", message = "Curriculum name has invalid characters.")
	@Size(min = 1, max = 128, message = "The name has to be between 1 and 128 characters in length.")
	private String name;

	@Column(name = "isActive")
	@NotNull(message = "The curriculum active value has to be set.")
	private Boolean isActive;

	@Column(name = "isCore")
	@NotNull(message = "The curriculum core value has to be set.")
	private Boolean isCore;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CURR_SKILLS")
	@NotNull(message = "A curriculum must have skills.")
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
