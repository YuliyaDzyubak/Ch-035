package com.crsms.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 
 * @author Valerii Motresku
 *
 */

@Entity
@Table(name="module")
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crsms_gen")
	@SequenceGenerator(name = "crsms_gen", sequenceName = "module_id_seq", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@OneToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	private Set<Resource> resources;
	
	@OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	private Set<Test> tests;
	
	@Column(nullable = false)
	private Boolean available = false;
	
	@Column(name = "order_position", nullable = true)
	private Long orderPosition;
	
	public Module() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<Test> getTests() {
		return tests;
	}

	public void setTests(Set<Test> tests) {
		this.tests = tests;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Long getOrderPosition() {
		return orderPosition;
	}

	public void setOrderPosition(Long orderPosition) {
		this.orderPosition = orderPosition;
	}


	
	
}
