package br.ufes.inf.nemo.mustwatch.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

@StaticMetamodel(Director.class)
public class Director_ extends PersistentObjectSupport_ {
	
	public static volatile SingularAttribute<Director, String> name;
	public static volatile SetAttribute<Director, Movie> movies;

}
