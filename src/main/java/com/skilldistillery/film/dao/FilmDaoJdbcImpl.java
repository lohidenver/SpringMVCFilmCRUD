package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Category;

public class FilmDaoJdbcImpl implements FilmDAO {
	String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	String user = "student";
	String pass = "student";

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

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getInt("release_year"), rs.getInt("language_id"),
							rs.getInt("rental_duration"), rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"), rs.getString("rating"),
							rs.getString("special_features"));
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

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // Start transaction
			String sql = "INSERT INTO film (title, description, release_year,"
					+ " language_id, rental_duration, rental_rate, length,"
					+ " replacement_cost, rating, special_features)" + " VALUES(?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			System.out.println(film.getReplacementCost());
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getReleaseYear());
			st.setInt(4, film.getLanguageId());
			st.setInt(5, film.getRentalDuration());
			st.setDouble(6, film.getRentalRate());
			st.setInt(7, film.getLength());
			st.setDouble(8, film.getReplacementCost());
			st.setString(9, film.getRating());
			st.setString(10, film.getSpecialFeatures());

			try {
				int cf = st.executeUpdate();
				conn.commit();
				System.out.println(cf + "Added A New Film To The Database.");

				ResultSet keys = st.getGeneratedKeys();
				while (keys.next()) {
					System.out.println("New film ID: " + keys.getInt(1));
				}
			} catch (SQLException e) {
				System.err.println("Error during Creation.");
				System.err.println("SQL Error: " + e.getErrorCode() + ": " + e.getMessage());
				System.err.println("SQL State: " + e.getSQLState());
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						System.err.println("Error rolling back.");
						e1.printStackTrace();
					}
				}
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Film deleteFilm(Film film) {
		Connection conn = null;
		Film filmToDelete = film;
		String sql = "DELETE FROM film WHERE film.id = ?";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, film.getId());
			int uc = stmt.executeUpdate();
			System.out.println(uc + " film records deleted");
			ResultSet keys = stmt.getGeneratedKeys();
			while (keys.next()) {
				System.out.println("Delete film ID: " + keys.getInt(1));
			}
			conn.commit();
		} catch (SQLException e) {
			System.err.println("Error during delete");
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.err.println("Error rolling back.");
					e1.printStackTrace();
				}
			}
			return null;
		}
		return filmToDelete;
	}

	@Override
	public Film updateFilm(Film film) {
		Connection conn = null;

		String sql = "UPDATE film SET title = ?,description = ?,release_year = ?,language_id = ?,"
				+ "rental_duration = ?,rental_rate = ?,length = ?,replacement_cost = ?,rating = ?,"
				+ "special_features = ? WHERE id = ?";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);

			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, film.getTitle());
			st.setString(2, film.getDescription());
			st.setInt(3, film.getReleaseYear());
			st.setInt(4, film.getLanguageId());
			st.setInt(5, film.getRentalDuration());
			st.setDouble(6, film.getRentalRate());
			st.setInt(7, film.getLength());
			st.setDouble(8, film.getReplacementCost());
			st.setString(9, film.getRating());
			st.setString(10, film.getSpecialFeatures());
			st.setInt(11, film.getId());
			System.out.println(film.getId());

			try {
				int uf = st.executeUpdate();
				conn.commit();
				System.out.println(uf + " film updated");

				ResultSet keys = st.getGeneratedKeys();
				while (keys.next()) {
					System.out.println("Updated film ID: " + keys.getInt(1));
				}
			} catch (SQLException e) {
				System.err.println("Error updating.");
				System.err.println("SQL Error: " + e.getErrorCode() + ": " + e.getMessage());
				System.err.println("SQL State: " + e.getSQLState());
				film = null;
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						System.err.println("Error rolling back.");
						e1.printStackTrace();
					}
				}
				return film;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	@Override
	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> filmList = new ArrayList<Film>();
		String searchPhrase = keyword;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT film.id, film.title, film.description, film.release_year,"
					+ " film.language_id, film.rental_duration, film.rental_rate, film.length,"
					+ " film.replacement_cost, film.rating, film.special_features, language.name "
					+ " FROM film JOIN language ON language.id = film.language_id"
					+ " WHERE film.title LIKE ? OR film.description LIKE ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + searchPhrase + "%");
			stmt.setString(2, "%" + searchPhrase + "%");
			ResultSet filmResult = stmt.executeQuery();

			while (filmResult.next()) {
				Film film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setSpecialFeatures(filmResult.getString("special_features"));
//				film.setFilmCast(findActorsByFilmId(filmResult.getInt("id")));
				filmList.add(film);
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Database error:");
			System.err.println(e);
		}
		return filmList;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT a.id, a.first_name, a.last_name FROM actor a JOIN film_actor f on a.id = f.actor_id where f.film_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					actors.add(new Actor(rs.getInt(1), rs.getString(2), rs.getString(3)));
				}
			} catch (SQLException e) {
				System.err.println("Database Error " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);
		}
		return actors;
	}

	@Override
	public List<Category> findCategoriesByFilmId(int filmId) {
		List<Category> categories = new ArrayList<>();
		String sql = "SELECT id, name FROM category c JOIN film_category f ON c.id = f.category_id WHERE f.film_id = ?";
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					categories.add(new Category(rs.getInt(1), rs.getString(2)));
				}
			} catch (SQLException e) {
				System.err.println("Database Error: " + e);
			}
		} catch (SQLException e) {
			System.err.println("Database Error: " + e);

		}
		return categories;
	}

}