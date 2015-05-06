package br.ufes.inf.nemo.mustwatch.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Movie extends PersistentObjectSupport implements Comparable<Movie>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@Basic
	@NotNull
	@Size(max = 300)
	private String original_title;
	
	@Basic
	@NotNull
	@Size(max = 300)
	private String portuguese_title;
	
	@Basic
	@NotNull
	@Size(max = 500)
	private String resume ;
	
	@Basic
	@NotNull
	private Integer lenght;
	
	
	@Column(nullable = false)
	@Size(max=1)
	private Integer isRelease;
	
	@Basic
	@NotNull
	@Size(max = 150)
	private String image;
	
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	private Director director;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@NotNull
	private DistributionCompany distributionCompany;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Country> countries;
	
	@OneToMany(mappedBy="owner")
	private Set<Rating> ratings;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Actor> actors;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Genre> genres;
	 
	public void setLenght(Integer lenght) {
		this.lenght = lenght;
	}

	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
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
	
	public int getLenght() {
		return lenght;
	}
	
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	
	public void addRating(Rating rating){
		if( this.ratings != null){
			ratings.add(rating);
		}else{
			HashSet<Rating> HashRatings = new HashSet<Rating>();
			HashRatings.add(rating);
		}
	}
	
	public Integer getIsRelease() {
		return isRelease;
	}

	public void setIsRelease(Integer isRelease) {
		this.isRelease = isRelease;
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
	
	public Director getDirector() {
		return director;
	}
	
	public void setDirector(Director director) {
		this.director = director;
	}
	
	public DistributionCompany getDistributionCompany() {
		return distributionCompany;
	}
	
	public void setDistributionCompany(DistributionCompany distributionCompany) {
		this.distributionCompany = distributionCompany;
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
