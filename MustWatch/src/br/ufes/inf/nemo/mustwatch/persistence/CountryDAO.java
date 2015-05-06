package br.ufes.inf.nemo.mustwatch.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.mustwatch.domain.Country;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface CountryDAO extends BaseDAO<Country> {

}
