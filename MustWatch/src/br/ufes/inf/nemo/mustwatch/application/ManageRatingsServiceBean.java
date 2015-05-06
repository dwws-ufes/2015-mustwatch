package br.ufes.inf.nemo.mustwatch.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.mustwatch.domain.Rating;
import br.ufes.inf.nemo.mustwatch.persistence.RatingDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Stateless
public class ManageRatingsServiceBean extends CrudServiceBean<Rating> implements
		ManageRatingsService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RatingDAO ratingDAO;
	
	@Override
	public BaseDAO<Rating> getDAO() {
		// TODO Auto-generated method stub
		return ratingDAO;
	}

	@Override
	protected Rating createNewEntity() {
		// TODO Auto-generated method stub
		return new Rating();
	}

}
