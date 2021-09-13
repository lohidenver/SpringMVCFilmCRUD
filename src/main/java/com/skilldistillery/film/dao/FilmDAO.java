package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Category;

public interface FilmDAO {

	Film findById(int filmId);

	Film createFilm(Film film);

	Film deleteFilm(Film film);

	Film updateFilm(Film film);

	List<Film> findFilmByKeyword(String keyword);

	List<Actor> findActorsByFilmId(int filmId);

	List<Category> findCategoriesByFilmId(int filmId);
	
	
}

