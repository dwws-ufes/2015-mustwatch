package br.ufes.inf.nemo.mustwatch.domain;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Genre extends PersistentObjectSupport implements Comparable<Genre>{

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@Basic
	@NotNull
	@Size(max = 200)
	private String name;
	
	@ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
	private Set<Movie> movies;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public int compareTo(Genre arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
