package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.mustwatch.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface UserDAO extends BaseDAO<User> {
	public User retrieveByLogin(String login);
}
