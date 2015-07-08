package br.ufes.inf.nemo.mustwatch.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Rating extends PersistentObjectSupport  implements Comparable<Rating> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	
	@Column(nullable = true) 
	private String comment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Movie movie;
	
	



	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setValue(Integer value) {
		this.value = value;
	}



	
	private Integer value;
	
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
	@Override
	public int compareTo(Rating o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
