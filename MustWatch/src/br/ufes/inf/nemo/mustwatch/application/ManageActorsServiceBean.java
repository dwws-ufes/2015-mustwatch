package br.ufes.inf.nemo.mustwatch.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.Actor;
import br.ufes.inf.nemo.mustwatch.persistence.ActorDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageActorsServiceBean extends CrudServiceBean<Actor> implements ManageActorsService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ActorDAO actorDAO;
	
	@Override
	public BaseDAO<Actor> getDAO() {
		// TODO Auto-generated method stub
		return actorDAO;
	}

	@Override
	protected Actor createNewEntity() {
		// TODO Auto-generated method stub
		return new Actor();
	}


}
