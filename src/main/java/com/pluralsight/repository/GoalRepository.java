package com.pluralsight.repository;

import java.util.List;

import com.pluralsight.model.Goal;

public interface GoalRepository {

	//nao precisa colocar que o metodo e publico, pois todos os metodos de uma interface sao publicos
	Goal save(Goal goal);

	List<Goal> loadAll();
}