package controller;

import java.io.Serializable;
import java.util.List;

import dao.ActorDao;
import entity.Actor;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("actorBean")
@SessionScoped
public class ActorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ActorDao dao;
	private Actor actor;

	public String create() {
		this.getDao().create(getActor());
		this.actor = new Actor();
		return "/actor/list";
	}
	
	public List<Actor> getRead() {
		return this.getDao().getAllActors();
	}

	public String updateForm(Actor actor) {
		this.actor = actor;
		return "/actor/update";
	}

	public String update() {
		this.getDao().update(actor);
		this.actor = new Actor();
		return "/actor/list";
	}

	public void delete(Actor actor) {
		this.getDao().delete(actor);
	}

	public ActorDao getDao() {
		if (this.dao == null) {
			this.dao = new ActorDao();
		}
		return dao;
	}

	public void setDao(ActorDao dao) {
		
		this.dao = dao;
	}

	public Actor getActor() {
		if (this.actor == null) {
			this.actor = new Actor();
		}
		return actor;
	}

	public void setActor(Actor Actor) {
		this.actor = Actor;
	}

}
