package br.ufes.inf.nemo.mustwatch.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;


@Entity
public class User extends PersistentObjectSupport implements Comparable<User>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	
	@Size(max = 200) 
	private String name;
	
	@Size(max = 100)
	private String email;
	
	@Size(max = 32)
	private String sex;
	
	@Size(max = 100)
	private String cpf;
	
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	private boolean isAdmin;
	
	@Size(max = 32) 
	private String password;
	
	@Size(max = 32) 
	private String login;
	
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


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}
	
	
}
