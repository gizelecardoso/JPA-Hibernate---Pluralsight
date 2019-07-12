package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;


@Entity//quero que o hibernate me rastreie
@Table(name = "goals")
@NamedQueries({
	@NamedQuery(name=Goal.FIND_GOAL_REPORTS, query="Select new com.pluralsight.model.GoalReport(g.minutes, e.minutes, e.activity)" + 
			"from Goal g, Exercise e where g.id = e.goal.id"),
	@NamedQuery(name=Goal.FIND_ALL_GOALS, query="Select g from Goal g")
	
})
public class Goal {
	
	public static final String FIND_ALL_GOALS = "findAllGoals";
	public static final String FIND_GOAL_REPORTS = "findGoalReports";
	
	@Id//minhsa PK
	@GeneratedValue//vai usar esse campo/coluna meu campo autoincremental
	@Column(name="GOAL_ID")
	private Long id;

	@Range(min = 1, max = 120)
	@Column(name="MINUTES")
	private int minutes;
	
	@OneToMany(mappedBy="goal", cascade=CascadeType.ALL, fetch=FetchType.LAZY)//fetch=FetchType.EAGER
					//erros no LAZY sao bem comuns para consertar precisamos usar a classe
						//OpenEntityManagerInViewFilter, implementando um filtro em nosso arquivo web.xml
	private List<Exercise> exercises = new ArrayList<Exercise>();

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}
	
}
