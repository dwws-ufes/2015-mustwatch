package br.ufes.inf.nemo.mustwatch.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageDirectorsService extends CrudService<Director> {
}
