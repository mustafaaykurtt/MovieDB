package dao;

import java.io.Serializable;
import java.util.List;

import entity.Language;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class LanguageDao implements Serializable {

	private static final long serialVersionUID = 1L;

	public void create(Language language) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			manager.getTransaction().begin();
			Language newLanguage = new Language();
			newLanguage.setName(language.getName());
			manager.persist(newLanguage);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public void update(Language language) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			Language a = manager.find(Language.class, language.getLanguageId());
			a.setName(language.getName());
			manager.getTransaction().begin();
			manager.merge(a);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public void delete(Language a) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			manager.getTransaction().begin();
			Language language = manager.getReference(Language.class, a.getLanguageId());
			manager.remove(language);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}
	}

	public Language find(long id) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			Language language = manager.find(Language.class, id);
			return language;
		} finally {
			manager.close();
		}
	}

	public List<Language> getAllLanguages() {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			String str = "select language from Language as language";
			TypedQuery<Language> query = manager.createQuery(str, Language.class);
			List<Language> languageList = query.getResultList();
			return languageList;
		} finally {
			manager.close();
		}
	}

	
	
	
	
	
	
	
}