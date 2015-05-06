package br.ufes.inf.nemo.mustwatch.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

@StaticMetamodel(Genre.class)
public class Genre_ extends PersistentObjectSupport_ {

	public static volatile SingularAttribute<Genre, String> name;
	public static volatile SetAttribute<Genre, Movie> movies;
}
