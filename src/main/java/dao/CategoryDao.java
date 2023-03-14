package dao;

import java.io.Serializable;
import java.util.List;


import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class CategoryDao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    public void create(Category category) {
        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
        	 manager.getTransaction().begin();
             Category newCategory = new Category();
             newCategory.setName(category.getName());
             newCategory.setLastUpdate(category.getLastUpdate());
             manager.persist(newCategory);
             manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }
    
    public void update(Category category) {
        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
        	Category c = manager.find(Category.class, category.getCategoryId());
    		c.setName(category.getName());
            manager.getTransaction().begin();
            manager.merge(c);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }
    
    public void delete(Category c) {
        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            manager.getTransaction().begin();
            Category category = manager.getReference(Category.class, c.getCategoryId());
            manager.remove(category);
            manager.getTransaction().commit();
        } finally {
            manager.close();
        }
    }
    
    public Category find(Category c) {
        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            Category category = manager.find(Category.class, c.getCategoryId());
            return category;
        } finally {
            manager.close();
        }
    }
    
    public List<Category> getAllCategories() {
        EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            String str = "select category from Category as category";
            TypedQuery<Category> query = manager.createQuery(str, Category.class);
            List<Category> categoryList = query.getResultList();
            return categoryList;
        } finally {
            manager.close();
        }
    }
}

