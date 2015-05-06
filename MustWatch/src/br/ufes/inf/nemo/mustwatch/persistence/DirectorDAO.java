package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface DirectorDAO extends BaseDAO<Director> {

}
