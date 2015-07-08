package br.ufes.inf.nemo.mustwatch.domain;

import java.util.ArrayList;
import java.util.List;
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
public class Director extends PersistentObjectSupport  implements Comparable<Director> {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 200)
	private String name;
	
	@ManyToMany(mappedBy = "directors", fetch = FetchType.EAGER)
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
	public int compareTo(Director o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	@Override
    public String toString() {
        return name;
    }
	
	
}
