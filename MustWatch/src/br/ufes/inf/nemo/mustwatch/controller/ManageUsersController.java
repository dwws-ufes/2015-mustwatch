package br.ufes.inf.nemo.mustwatch.controller;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageUsersService;
import br.ufes.inf.nemo.mustwatch.domain.User;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.SimpleFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named("manageUsersController")
@SessionScoped
public class ManageUsersController extends CrudController<User> {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@EJB 
	ManageUsersService manageUsersService;
	private String password1;
	private Date birthDate;
	private String sex;
	
	public ManageUsersController() {
	    viewPath = "/core/manageUsers/";
	    bundleName = "msgs";
	}
	
	@Override
	protected User createNewEntity() {
		return new User();
	}

	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
	}

	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("manageUser.filter.byName", "name", getI18nMessage("msgs", "manageUser.text.filter.byName")));
		addFilter(new SimpleFilter("manageUser.filter.bySex", "sex", getI18nMessage("msgs", "manageUser.text.filter.bySex")));
		
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
