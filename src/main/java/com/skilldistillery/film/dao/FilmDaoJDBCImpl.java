package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skilldistillery.film.entities.Film;

public class FilmDaoJDBCImpl implements FilmDAO{

	@Override
	public Film findById(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Film findFilmById(int filmId) {
//		Film film = null;
//		Connection conn;
//		try {
//			conn = DriverManager.getConnection(URL, user, pass);
//
//			String sql = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
//
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, filmId);//			
//			ResultSet rs = stmt.executeQuery();
//			if (rs.next()) {
//				film = new Film();
//				film.setId(rs.getInt("id"));
//				film.setTitle(rs.getString("title"));
//				film.setDescription(rs.getString("description"));
//				film.setReleaseYear(rs.getInt("release_year"));
//				film.setLanguage(rs.getString("name"));
//				film.setRentalDuration(rs.getInt("rental_duration"));
//				film.setRentalRate(rs.getDouble("rental_rate"));
//				film.setLength(rs.getInt("length"));
//				film.setReplacementCost(rs.getDouble("replacement_cost"));
//				film.setRating(rs.getString("rating"));
//				film.setSpecialFeatures(rs.getString("special_features"));
//				
	
	
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//
//		} catch (SQLException e) {
//			System.out.println("Database Error: ");
//			System.out.println(e);
//		}
//
//		return film;
//
//	}
	
}
