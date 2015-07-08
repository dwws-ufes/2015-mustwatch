package br.ufes.inf.nemo.mustwatch.utils;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


import br.ufes.inf.nemo.mustwatch.application.ManageCountriesService;
import br.ufes.inf.nemo.mustwatch.domain.Country;
import br.ufes.inf.nemo.mustwatch.domain.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class JenaQueryController {
	
	 
	
	public static void main(String[] args) throws SQLException {
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/mustwatch?zeroDateTimeBehavior=convertToNull";
        String user = "mustwatch";
        String password = "mustwatch";
        String querysql = "INSERT INTO `mustwatch`.`country` (`id`, `uuid`, `version`, `name`) VALUES (?, ?, ?, ?);";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            st = con.createStatement();
        } catch (ClassNotFoundException ex) {
            
        } catch (SQLException exx) {
            
        }

		String countryString = "select distinct ?countryName ?ano where { ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/country>. ?x <http://data.linkedmdb.org/resource/movie/country_name> ?countryName .}";
		Query query = QueryFactory.create(countryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		
		String countryName = null;
		int count = 0;
        PreparedStatement preparedStmt;
        preparedStmt = con.prepareStatement(querysql);
		try {
			  ResultSet results = qexec.execSelect();
	          while (results.hasNext()) {
	              count++;
	              QuerySolution row = results.next();
	              
                  RDFNode value = row.get("countryName");
                  
            	  if (value != null){
                	  if (value.isLiteral()) {
                		  countryName = ((Literal) value).getString().replace("\n", "");
                      } else if (value.isURIResource()) {
                    	  countryName = ((Resource) value).getLocalName().replace("\n", "");
                      } else {
                    	  countryName = value.toString().replace("\n", "");
                      }
            	  }
            	  
	              try {
	                    
	                    
                    preparedStmt.setLong(1, new Long(count));
                    preparedStmt.setString(2, "8"+count+"jjoi");
                    preparedStmt.setLong(3, new Long(0));
                    preparedStmt.setString(4, countryName);

                    preparedStmt.addBatch();
                    if (count == 1000) {
                        preparedStmt.executeBatch();
                        con.commit();
                        count = 0;
                    }

                } catch (SQLException ex) {
                    
                }
	              
	          }
	          
	      } finally {
	    	  if(count!=0){
	                preparedStmt.executeBatch();
	                con.commit();
	                count = 0;
	            }
	          qexec.close();
	      }
	}
}
