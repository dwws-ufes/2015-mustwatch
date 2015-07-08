package br.ufes.inf.nemo.mustwatch.application;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.mustwatch.persistence.DirectorDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageDirectorsServiceBean extends CrudServiceBean<Director> implements
		ManageDirectorsService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DirectorDAO directorDAO;
	
	@Override
	public BaseDAO<Director> getDAO() {
		// TODO Auto-generated method stub
		return directorDAO;
	}

	@Override
	protected Director createNewEntity() {
		// TODO Auto-generated method stub
		return new Director();
	}

}
