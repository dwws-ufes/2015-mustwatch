package br.ufes.inf.nemo.mustwatch.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Movie extends PersistentObjectSupport implements Comparable<Movie>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	
	@Size(max = 300)
	private String original_title;
	
	
	
	@Size(max = 300)
	private String portuguese_title;
	
	
	
	@Size(max = 500)
	private String resume ;
	
	
	
	private Integer lenght;
	
	@Size(max = 150)
	private String image;
	
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		      name="movie_director",
		      joinColumns={@JoinColumn(name="movie_id", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="director_id", referencedColumnName="id")},
		      uniqueConstraints={@UniqueConstraint(columnNames = {"movie_id", "director_id"})})
	private Set<Director> directors;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private DistributionCompany distributionCompany;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		      name="movie_country",
		      joinColumns={@JoinColumn(name="movie_id", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="country_id", referencedColumnName="id")},
		      uniqueConstraints={@UniqueConstraint(columnNames = {"movie_id", "country_id"})})
	private Set<Country> countries;
	
	
	
	@OneToMany(mappedBy = "movie",fetch = FetchType.EAGER)
	private Set<Rating> ratings;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		      name="movie_actors",
		      joinColumns={@JoinColumn(name="movie_id", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="actor_id", referencedColumnName="id")},
		      uniqueConstraints={@UniqueConstraint(columnNames = {"movie_id", "actor_id"})})
	private Set<Actor> actors;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		      name="movie_genres",
		      joinColumns={@JoinColumn(name="movie_id", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="genre_id", referencedColumnName="id")},
		      uniqueConstraints={@UniqueConstraint(columnNames = {"movie_id", "genre_id"})})
	private Set<Genre> genres;
	 
	
	public void addRating(Rating rating){
		if (ratings == null) {
			ratings = new HashSet<Rating>();
		}
		ratings.add(rating);
		rating.setMovie(this);
	}
	

//	public void addDirectors(Director director){
//		boolean alreadyinserted = false;
//		if (directors == null) {
//			directors = new ArrayList<Director>();
//		}
//		for (Director directorI : directors) {
//			if(directorI.getName().equals(director.getName())){
//				alreadyinserted = true;
//			}
//		}
//		if(!alreadyinserted){
//			directors.add(director);
//			director.addMovies(this);
//		}
//	}
	
	
	public String getOriginal_title() {
		return original_title;
	}



	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}



	public String getPortuguese_title() {
		return portuguese_title;
	}



	public void setPortuguese_title(String portuguese_title) {
		this.portuguese_title = portuguese_title;
	}



	public String getResume() {
		return resume;
	}



	public void setResume(String resume) {
		this.resume = resume;
	}



	public Integer getLenght() {
		return lenght;
	}



	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}



	


	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public Date getReleaseDate() {
		return releaseDate;
	}



	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}



	


	public DistributionCompany getDistributionCompany() {
		return distributionCompany;
	}



	public void setDistributionCompany(DistributionCompany distributionCompany) {
		this.distributionCompany = distributionCompany;
	}



	



	public Set<Director> getDirectors() {
		return directors;
	}


	public void setDirectors(Set<Director> directors) {
		this.directors = directors;
	}


	public Set<Country> getCountries() {
		return countries;
	}


	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}


	public Set<Rating> getRatings() {
		return ratings;
	}


	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}


	public Set<Actor> getActors() {
		return actors;
	}


	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}


	public Set<Genre> getGenres() {
		return genres;
	}


	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}


	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
