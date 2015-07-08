package br.ufes.inf.nemo.mustwatch.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class test {
	
	public static void main(String[] args) throws SQLException {
		

		String withoutFilter = "select ?i where { "
				+ "?i <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/actor> ."
				+ "?i <http://data.linkedmdb.org/resource/movie/actor_name> \"Gloria Stuart\" . }";
		
		String withFilter = "select ?i where { "
				+ "?i <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/actor> ."
				+ "?i <http://data.linkedmdb.org/resource/movie/actor_name> ?u . "
				+ "filter regex(?u,\"Leonardo DiCaprio\",\"i\") .}";
		
		
		Query query = QueryFactory.create(withFilter);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		
		try {
			  ResultSet results = qexec.execSelect();
	          System.out.println(ResultSetFormatter.asText(results));
	          
	      } finally {
	    	  
	          qexec.close();
	      }
	}

}
