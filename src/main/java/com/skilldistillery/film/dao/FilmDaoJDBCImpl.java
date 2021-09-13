package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDaoJDBCImpl implements FilmDAO {
	String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	String user = "student";
	String pword = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findById(int filmId) {
		Film film = null;
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, "
				+ "length, replacement_cost, rating, special_features FROM film where id = ?";

		try (Connection conn = DriverManager.getConnection(url, user, pword);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					film = new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
							rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
							rs.getString(11));
						rs.getString("special_features");
				}
			} catch (SQLException e) {
				System.err.println("Database error: " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);
		}
		return film;
	}

	@Override
	public Film createFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFilm(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Film updateFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}


//	@Override
//	public List<Film> findFilmsByKeyword(String keyword) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Actor findActorById(int actorId) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Actor> findActorsByFilmId(int filmId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
}