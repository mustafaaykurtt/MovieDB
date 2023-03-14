package controller;

import java.io.Serializable;
import java.util.List;

import dao.LanguageDao;
import entity.Language;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("languageBean")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private LanguageDao dao;
	private Language language;

	public String create() {
		this.getDao().create(getLanguage());
		this.language = new Language();
		return "/language/list";
	}

	public List<Language> getRead() {
		return this.getDao().getAllLanguages();
	}

	public String updateForm(Language language) {
		this.language = language;
		return "/language/update";
	}
	
	public Language getById(long id) {
		return this.getDao().find(id);
	}

	public String update() {
		this.getDao().update(language);
		this.language = new Language();
		return "/language/list";
	}

	public void delete(Language language) {
		this.getDao().delete(language);
	}

	public LanguageDao getDao() {
		if (this.dao == null) {
			this.dao = new LanguageDao();
		}
		return dao;
	}

	public void setDao(LanguageDao dao) {
		
		this.dao = dao;
	}

	public Language getLanguage() {
		if (this.language == null) {
			this.language = new Language();
		}
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
