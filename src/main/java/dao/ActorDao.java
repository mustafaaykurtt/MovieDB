package dao;

import java.io.Serializable;
import java.util.List;

import entity.Actor;
import jakarta.persistence.*;
import util.JPAUtil;

public class ActorDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public void create(Actor actor) {
	        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	        	 manager.getTransaction().begin();
	        	 Actor newActor = new Actor();
	        	 newActor.setFirstName(actor.getFirstName());
	        	 newActor.setLastName(actor.getLastName());
	        	 newActor.setLastUpdate(actor.getLastUpdate());
	             manager.persist(newActor);
	             manager.getTransaction().commit();
	        } finally {
	            manager.close();
	        }
	    }
	    
	    public void update(Actor actor) {
	        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	        	Actor a = manager.find(Actor.class, actor.getActorId());
	    		a.setFirstName(actor.getFirstName());
	    		a.setLastName(actor.getLastName());
	            manager.getTransaction().begin();
	            manager.merge(a);
	            manager.getTransaction().commit();
	        } finally {
	            manager.close();
	        }
	    }
	    
	    public void delete(Actor a) {
	        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	            manager.getTransaction().begin();
	            Actor actor = manager.getReference(Actor.class, a.getActorId());
	            manager.remove(actor);
	            manager.getTransaction().commit();
	        } finally {
	            manager.close();
	        }
	    }
	    
	    public Actor find(Actor a) {
	        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	        	Actor actor = manager.find(Actor.class, a.getActorId());
	            return actor;
	        } finally {
	            manager.close();
	        }
	    }
	    
	    public List<Actor> getAllActors() {
	        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	            String str = "select actor from Actor as actor";
	            TypedQuery<Actor> query = manager.createQuery(str, Actor.class);
	            List<Actor> actorList = query.getResultList();
	            return actorList;
	        } finally {
	            manager.close();
	        }
	    }
}
