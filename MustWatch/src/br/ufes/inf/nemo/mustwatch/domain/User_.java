package br.ufes.inf.nemo.mustwatch.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

@StaticMetamodel(User.class)
public class User_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<User, String> name;
	
	public static volatile SingularAttribute<User,String> email;
	
	public static volatile SingularAttribute<User, String> sex;
	
	public static volatile SingularAttribute<User,String> cpf;

	public static volatile SingularAttribute<User, String> password;
	
	public static volatile SingularAttribute<User,Date> birthDate;
		
	public static volatile SingularAttribute<User,Boolean> isAdmin;

	public static volatile SingularAttribute<User, String> login;
}
