package br.ufes.inf.nemo.mustwatch.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.User;
import br.ufes.inf.nemo.mustwatch.persistence.UserDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@EJB 
	private UserDAO userDAO;

	@Override
	public BaseDAO<User> getDAO() {
		// TODO Auto-generated method stub
		return userDAO;
	}

	@Override
	protected User createNewEntity() {
		// TODO Auto-generated method stub
		return new User();
	}

	@Override
	public User retrieveByLogin(String login) {
		return userDAO.retrieveByLogin(login);
	}

}
