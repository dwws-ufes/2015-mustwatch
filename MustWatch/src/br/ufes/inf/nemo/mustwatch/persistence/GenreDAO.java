package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.mustwatch.domain.Genre;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface GenreDAO extends BaseDAO<Genre> {

}
