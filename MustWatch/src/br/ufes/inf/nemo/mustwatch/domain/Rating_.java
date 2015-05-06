package br.ufes.inf.nemo.mustwatch.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

@StaticMetamodel(Rating.class)
public class Rating_ extends PersistentObjectSupport_ {

	public static volatile SingularAttribute<Rating, Movie> movie;
	public static volatile SingularAttribute<Rating, String> comment;
	public static volatile SingularAttribute<Rating, Integer> value;
//	public static volatile SingularAttribute<Rating, User> user;
	
}
