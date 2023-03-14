package dao;

import java.io.Serializable;
import java.util.List;

import entity.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class FilmDao implements Serializable {

	private static final long serialVersionUID = 1L;

	public void create(Film film) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			manager.getTransaction().begin();
			Film newFilm = new Film();
			newFilm.setTitle(film.getTitle());
			newFilm.setDescription(film.getDescription());
			newFilm.setReleaseYear(film.getReleaseYear());
			newFilm.setLanguageId(film.getLanguageId());
			newFilm.setLength(film.getLength());
			manager.persist(newFilm);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public void update(Film film) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			Film newFilm = manager.find(Film.class, film.getFilmId());
			newFilm.setTitle(film.getTitle());
			newFilm.setDescription(film.getDescription());
			newFilm.setReleaseYear(film.getReleaseYear());
			newFilm.setLanguageId(film.getLanguageId());
			newFilm.setLength(film.getLength());
			manager.getTransaction().begin();
			manager.merge(newFilm);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public void delete(Film a) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			manager.getTransaction().begin();
			Film film = manager.getReference(Film.class, a.getFilmId());
			manager.remove(film);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public Film find(Film a) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			Film film = manager.find(Film.class, a.getFilmId());
			return film;
		} finally {
			manager.close();
		}
	}

	public List<Film> getAllFilms() {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			String str = "select film from Film as film";
			TypedQuery<Film> query = manager.createQuery(str, Film.class);
			List<Film> FilmList = query.getResultList();
			return FilmList;
		} finally {
			manager.close();
		}
	}
}
