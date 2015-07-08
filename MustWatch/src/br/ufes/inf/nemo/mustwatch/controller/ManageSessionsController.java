package br.ufes.inf.nemo.mustwatch.controller;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageUsersService;
import br.ufes.inf.nemo.mustwatch.domain.User;
import br.ufes.inf.nemo.mustwatch.persistence.UserJPADAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@SessionScoped
@Named("manageSessionsController")
public class ManageSessionsController extends CrudController<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8055269771282377969L;
	@EJB
	ManageUsersService manageUsersService;
	private User currentUser;
	private String login;
	private String password;
	
	public boolean isAdmin(){
		return currentUser.isAdmin();
	}
	
	public void verifyLogin(){
		try {
			
			User user = manageUsersService.retrieveByLogin(login);
			if (user.getPassword().equals(password)) {
				currentUser = user;
				if(user.isAdmin()){
					FacesContext.getCurrentInstance().getExternalContext().redirect("core/manageMovies/list.faces");
				}
				else{
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
				}
			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ocorreu um erro ao efetuar o login. Por favor tente novamente."));
		}
	}
	
	public String logout(){
		currentUser = null;
		return "/login.faces";
	}
	
	@Override
	protected User createNewEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
		}
	
	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public ManageUsersService getManageUsersService() {
		return manageUsersService;
	}

	public void setManageUsersService(ManageUsersService manageUsersService) {
		this.manageUsersService = manageUsersService;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
