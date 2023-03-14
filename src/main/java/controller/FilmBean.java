package controller;

import java.io.Serializable;
import java.util.List;

import dao.FilmDao;
import entity.Film;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("filmBean")
@SessionScoped
public class FilmBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FilmDao dao;
	private Film film;

	public String create() {
		this.getDao().create(getFilm());
		this.film = new Film();
		return "/film/list";
	}

	public List<Film> getRead() {
		return this.getDao().getAllFilms();
	}

	public String updateForm(Film film) {
		this.film = film;
		return "/film/update";
	}

	public String update() {
		this.getDao().update(film);
		this.film = new Film();
		return "/film/list";
	}

	public void delete(Film film) {
		this.getDao().delete(film);
	}

	public FilmDao getDao() {
		if (this.dao == null) {
			this.dao = new FilmDao();
		}
		return dao;
	}

	public void setDao(FilmDao dao) {
		
		this.dao = dao;
	}

	public Film getFilm() {
		if (this.film == null) {
			this.film = new Film();
		}
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}
