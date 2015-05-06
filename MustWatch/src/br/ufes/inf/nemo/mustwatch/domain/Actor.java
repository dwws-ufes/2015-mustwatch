package br.ufes.inf.nemo.mustwatch.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Actor extends PersistentObjectSupport implements Comparable<Actor>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@Basic
	@NotNull
	@Size(max = 200)
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Movie> movies;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Country o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public int compareTo(Actor o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
