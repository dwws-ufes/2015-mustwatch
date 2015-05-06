package br.ufes.inf.nemo.mustwatch.domain;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

@StaticMetamodel(Movie.class)
public class Movie_ extends PersistentObjectSupport_ {
	
	public static volatile SingularAttribute<Movie, String> original_title;
	public static volatile SingularAttribute<Movie, String> portuguese_title;
	public static volatile SingularAttribute<Movie, String> resume;
	public static volatile SingularAttribute<Movie, Integer> lenght;
	public static volatile SingularAttribute<Movie, Integer> isRelease;
	public static volatile SingularAttribute<Movie, DistributionCompany> distributionCompany;
	public static volatile SingularAttribute<Movie, Director> director;
	public static volatile SingularAttribute<Movie, String> image;
	public static volatile SingularAttribute<Movie, Date> releaseDate;

	public static volatile SetAttribute<Movie, Rating> ratings;
	public static volatile SetAttribute<Movie, Country> countries;
	public static volatile SetAttribute<Movie, Actor> actors;
	public static volatile SetAttribute<Movie, Genre> genres;

}


