package br.ufes.inf.nemo.mustwatch.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

@StaticMetamodel(Country.class)
public class Country_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Country, String> name;
	public static volatile SetAttribute<Country, Movie> movies;

}
