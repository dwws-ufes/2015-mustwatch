package br.ufes.inf.nemo.mustwatch.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.mustwatch.domain.Actor;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageActorsService extends CrudService<Actor>{

}
