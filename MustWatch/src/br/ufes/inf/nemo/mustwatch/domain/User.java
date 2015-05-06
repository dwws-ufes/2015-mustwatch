package br.ufes.inf.nemo.mustwatch.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;


@Entity
public class User extends PersistentObjectSupport implements Comparable<User>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@Basic
	@Size(max = 200)
	@Column(nullable = false) 
	private String name;
	
	@Basic
	@Size(max = 100)
	@Column(nullable = false) 
	private String email;
	
	@Basic
	@Size(max = 32)
	@Column(nullable = false) 
	private String sex;
	
	@Basic
	@Size(max = 100)
	@Column(nullable = false) 
	private String cpf;
	
	
	@Column(nullable = false) 
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	
	@Column(nullable = false) 
	private boolean isAdmin;
	
	@Basic
	@Size(max = 32)
	@Column(nullable = false) 
	private String password;
	
	
	@Override
	public int compareTo(User arg0) {
		if (name == null) return 1;
		if(arg0.name == null) return -1;
		int cmp = name.compareTo(arg0.name);
		if(cmp != 0) return cmp;
		
		return uuid.compareTo(arg0.uuid);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
