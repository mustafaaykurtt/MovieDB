package controller;

import java.io.Serializable;
import java.util.List;

import dao.CategoryDao;
import entity.Category;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("categoryBean")
@SessionScoped
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CategoryDao dao;
	private Category category;

	public String create() {
		this.getDao().create(getCategory());
		this.category = new Category();
		return "/category/list";
	}

	public List<Category> getRead() {
		return this.getDao().getAllCategories();
	}

	public String updateForm(Category category) {
		this.category = category;
		return "/category/update";
	}

	public String update() {
		this.getDao().update(category);
		this.category = new Category();
		return "/category/list";
	}

	public void delete(Category category) {
		this.getDao().delete(category);
	}

	public CategoryDao getDao() {
		if (this.dao == null) {
			this.dao = new CategoryDao();
		}
		return dao;
	}

	public void setDao(CategoryDao dao) {
		
		this.dao = dao;
	}

	public Category getCategory() {
		if (this.category == null) {
			this.category = new Category();
		}
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
