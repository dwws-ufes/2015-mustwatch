package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.mustwatch.domain.Rating;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface RatingDAO extends BaseDAO<Rating> {

}
