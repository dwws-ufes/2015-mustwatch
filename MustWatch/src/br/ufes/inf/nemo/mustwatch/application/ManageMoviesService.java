package br.ufes.inf.nemo.mustwatch.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.mustwatch.domain.Movie;

@Local
public interface ManageMoviesService extends CrudService<Movie> {

}
