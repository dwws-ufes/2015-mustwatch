package br.ufes.inf.nemo.mustwatch.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import br.ufes.inf.nemo.mustwatch.application.ManageMoviesService;
import br.ufes.inf.nemo.mustwatch.application.ManageRatingsService;
import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.mustwatch.domain.DistributionCompany;
import br.ufes.inf.nemo.mustwatch.domain.Movie;
import br.ufes.inf.nemo.mustwatch.domain.Rating;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.SimpleFilter;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named("manageMoviesController")
@SessionScoped
public class ManageMoviesController extends CrudController<Movie>{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	

	@EJB
	ManageMoviesService manageMoviesService;
	
	@EJB
	ManageRatingsService manageRatingsService;
	
	private List<String> selectedOptions;
    private List<Director> selectedDirectors;
    private List<Director> directors;
	private List<Movie> movies;
	private List<Movie> movies6;
	private Movie selectedMovie;
	private String filteredName;
	private Integer rating = 4;
	private String comment = "";
	
	
	
	public List<String> getSugestions(String movie){
		
		String filme_ano1 = "select distinct ?movieName ?ano where { ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/film>. ?x <http://purl.org/dc/terms/title> ?movieName . filter regex(?movieName,\"";
		String filme_ano2 = "\",\"i\") .optional{?x <http://data.linkedmdb.org/resource/movie/initial_release_date> ?ano .}}";
		Query query = QueryFactory.create(filme_ano1 +movie+filme_ano2);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		String[] varNames = {"movieName","ano"};
		String movieName = null,ano=null;
		
		List<String> sugestionStrings = new ArrayList<String>();
		try {
			  ResultSet results = qexec.execSelect();
	          while (results.hasNext()) {
	              
	              QuerySolution row = results.next();
	              for (String var : varNames) {
	                  RDFNode value = row.get(var);
	                  if (var.equals("movieName")) {
	                	  if (value != null){
		                	  if (value.isLiteral()) {
		                          movieName = ((Literal) value).getString().replace("\n", "");
		                      } else if (value.isURIResource()) {
		                    	  movieName = ((Resource) value).getLocalName().replace("\n", "");
		                      } else {
		                    	  movieName = value.toString().replace("\n", "");
		                      }
	                	  }
	                  } else if (var.equals("ano")) {
	                	  if(value != null){
	                		  ano = value.toString();
	                	  }
	                  }
	              }
	              sugestionStrings.add(movieName+" ("+ano+")");
	          }
	          return sugestionStrings;
	      } finally {
	          qexec.close();
	      }
	}
	public void onItemSelect(SelectEvent event){
		  String movieAge = event.getObject().toString();
		  String movieName, age;
		  age = movieAge.substring(movieAge.indexOf('(')+1,movieAge.lastIndexOf(')'));
		  movieName = movieAge.substring(0,movieAge.indexOf('(')-1);
		      
		  
		  String q1 = "select distinct ?filme where {"
		    + " ?filme <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/film>."
		    + " ?filme <http://purl.org/dc/terms/title> ?movieName ."
		    + " filter regex(?movieName,\"";
		  String q2 = "\",\"i\") .optional{?filme <http://data.linkedmdb.org/resource/movie/initial_release_date> ?ano .} filter regex(?ano,\"";
		     String q3 = "\",\"i\") .}";
		     
		     String filme = q1 + movieName + q2 + age + q3;
		     String filmeurl = "";
		     
		     
		     
		     Query query = QueryFactory.create(filme);
		  QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		  try {
		    ResultSet results = qexec.execSelect();
		    while (results.hasNext()) {
		        
		     QuerySolution row = results.next();
		           RDFNode value = row.get("filme");
		    
		     if (value != null){
		      filmeurl = "<"+value.toString()+">";
		      getSelectedEntity().setPortuguese_title("adquira mais informações em: "+value.toString());
		              }
		    }
		    
		    String runtimeString = "select distinct ?runtime where {"
		     + filmeurl + " <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/film>."
		     + filmeurl + " <http://data.linkedmdb.org/resource/movie/runtime> ?runtime .}";
		   query = QueryFactory.create(runtimeString);
		    qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		    results = qexec.execSelect();
		     while (results.hasNext()) {
		         
		      QuerySolution row = results.next();
		            RDFNode value = row.get("runtime");
		     
		      if (value != null){
		       getSelectedEntity().setLenght(Integer.parseInt(value.toString()));
		               }
		     }
		    
		           
		      } finally {
		          qexec.close();
		      }
		 }	
	public Integer getRating() {
		return rating;
	}

	public void doRate(){
		if(selectedMovie != null){
			manageMoviesService.doRateService(selectedMovie.getId(), rating,comment);
		}else{
			System.out.println("rwesadawdaw");
		}
	}

	public String getFilteredName() {
		return filteredName;
	}

	public void setFilteredName(String filteredName) {
		this.filteredName = filteredName;
	}

	public void init() {
    	int i = 0;
    	movies = new ArrayList<Movie>();
    	movies6 = new ArrayList<Movie>();
        getLastEntityIndex();
        movies = manageMoviesService.getDAO().retrieveAll();
        while(i<6 && i<movies.size()){
        	movies6.add(movies.get(i));
        	i++;
        }
        selectedDirectors = new ArrayList<Director>();
    }
	
	public void deselect(){
		super.selectedEntity = null;
	}
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Movie Selected", ((Movie) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public Movie getParamMovie(Long id){
    	retrieveExistingEntity(id);
    	selectedMovie = selectedEntity;
    	selectedEntity = null;
    	return selectedMovie;
    }
    
    public Movie getSelectedMovie() {
		return selectedMovie;
	}



    public String movieStatus(Integer index){
    	if((index+1)%6 == 0){
    		return "last movie";
    	}
    	return "movie";
    	
    }

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Movie> getMovies() {
		return movies;
	}



	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public void setMovies6(List<Movie> movies6) {
		this.movies6 = movies6;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public List<Movie> getMovies6() {
		return movies6;
	}




	@Override
	protected Movie createNewEntity() {
		return new Movie();
	}

	@Override
	protected CrudService<Movie> getCrudService() {
		return manageMoviesService;
	}

	public ManageMoviesController(){
		viewPath = "/core/manageMovies/";
	    bundleName = "msgs";
	}

	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("manageMovies.filter.byOriginalTitle", "original_title", getI18nMessage("msgs", "manageMovies.text.filter.byOriginalTitle")));
	}

	public List<Director> getSelectedDirectors() {
		return selectedDirectors;
	}

	public void setSelectedDirectors(List<Director> selectedDirectors) {
		this.selectedDirectors = selectedDirectors;
	}

	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	public List<String> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(List<String> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}
	
	
	
}
