package br.ufes.inf.nemo.mustwatch.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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


import br.ufes.inf.nemo.mustwatch.domain.Movie;

public class JenaQueryController {
	
	public static void main(String[] args) {

      
      String filme_actor = "select distinct ?z ?ano ?i where { ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/film>. ?x <http://purl.org/dc/terms/title> ?z . filter regex(?z,\"titanic\",\"i\") .optional{?x <http://data.linkedmdb.org/resource/movie/initial_release_date> ?ano} .optional{?x <http://data.linkedmdb.org/resource/movie/actor> ?t . ?t <http://data.linkedmdb.org/resource/movie/actor_name> ?i}}order by (?z)";
      String filme_director = "select distinct ?movieName ?ano ?actorName ?directorName where {\n"
      		+ "?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/film> .\n"
      		+ "?x <http://purl.org/dc/terms/title> ?movieName .\n"
      		+ "optional{?x <http://data.linkedmdb.org/resource/movie/actor> ?ac .\n"
      		+ "?ac <http://data.linkedmdb.org/resource/movie/actor_name> ?actorName} .\n"
      		+ "optional{?x <http://data.linkedmdb.org/resource/movie/initial_release_date> ?ano} .\n"
      		+ "optional{?x <http://data.linkedmdb.org/resource/movie/director> ?t .\n"
      		+ "?t <http://data.linkedmdb.org/resource/movie/director_name> ?directorName .}\n"
      		+ "filter regex(?movieName,\"titanic\",\"i\") .\n"
      		+ "}order by ?movieName ?actorName ?directorName";
      
      String filme_ano = "select distinct ?ano where { <http://data.linkedmdb.org/resource/film/72> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/film>. optional{<http://data.linkedmdb.org/resource/film/72> <http://data.linkedmdb.org/resource/movie/initial_release_date> ?ano .} filter regex(?actorName.\"titanic\",\"i\")}";
      String test = "select * where {<http://data.linkedmdb.org/resource/movie/film> ?y ?z .}";
      Query query = QueryFactory.create(filme_director);
  // initializing queryExecution factory with remote service.
      // **this actually was the main problem I couldn't figure out.**
      QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);

  //after it goes standard query execution and result processing which can
      // be found in almost any Jena/SPARQL tutorial.
      String[] varNames = {"movieName","ano","actorName","directorName"};
      String movieName = null,ano=null,actorName=null,directorName=null;
      Movie movie = new Movie();
      List<Movie> movies = new ArrayList<Movie>();
      String lastMovieName = null,lastAno=null,lastActorName=null,lastDirectorName=null;
      try {
          ResultSet results = qexec.execSelect();
          while (results.hasNext()) {
              
              QuerySolution row = results.next();
              for (String var : varNames) {
                  RDFNode value = row.get(var);
                  if (var.equals("movieName")) {
                	  if (value.isLiteral()) {
                          movieName = ((Literal) value).getString().replace("\n", "");
                      } else if (value.isURIResource()) {
                    	  movieName = ((Resource) value).getLocalName().replace("\n", "");
                      } else {
                    	  movieName = value.toString().replace("\n", "");
                      }
                  } else if (var.equals("ano")) {
                	  if(Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", value.toString())){
                		  ano = value.toString();
                	  }else if(Pattern.matches("[0-9]{4}", value.toString())){
                		  ano = value.toString() + "01-01";
                	  }
                  } else if (var.equals("actorName")){
                	  if (value.isLiteral()) {
                          actorName = ((Literal) value).getString().replace("\n", "");
                      }else{
                    	  actorName = value.toString().replace("\n", "");
                      }
                  } else if (var.equals("directorName")){
                	  if (value.isLiteral()) {
                		  directorName = ((Literal) value).getString().replace("\n", "");
                      }else{
                    	  directorName = value.toString().replace("\n", "");
                      }
                  }
              }
              
              if(movieName != lastMovieName){
            	  movie = new Movie();
            	  movie.setOriginal_title(movieName);
            	  lastMovieName = movieName;
              }
              
              movie.setReleaseDate(new Date(ano));
              Date date = new Date();
             
              System.out.println(movie.getReleaseDate().getYear());
              
              

          }
      } finally {
          qexec.close();
      }
  }
}
