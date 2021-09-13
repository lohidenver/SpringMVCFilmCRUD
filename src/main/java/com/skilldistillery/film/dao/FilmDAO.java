package com.skilldistillery.film.dao;

import com.skilldistillery.film.entities.Film;

public interface FilmDAO {

	Film findById(int filmId);

	Film createFilm(Film film);

	public boolean deleteFilm(Film film);

	Film updateFilm(Film film);
	
}
