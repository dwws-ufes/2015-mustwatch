package br.ufes.inf.nemo.mustwatch.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import br.ufes.inf.nemo.mustwatch.application.ManageDirectorsService;
import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.mustwatch.domain.Movie;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;

@Named("manageDirectorsController")
@SessionScoped
public class ManageDirectorsController extends CrudController<Director> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	private List<Director> allDirectors;
	
	@EJB
	private ManageDirectorsService manageDirectorsService;
	
	public void init() {
		allDirectors = manageDirectorsService.getDAO().retrieveAll();
    }
	
	public List<String> getSugestions(String director){
		
		String filme_ano1 = "select distinct ?directorName ?ano where { ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/director>. ?x <http://data.linkedmdb.org/resource/movie/director_name> ?directorName . filter regex(?directorName,\"";
		String filme_ano2 = "\",\"i\")}";
		Query query = QueryFactory.create(filme_ano1 +director+filme_ano2);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		
		String directorName = null;
		
		List<String> sugestionStrings = new ArrayList<String>();
		try {
			  ResultSet results = qexec.execSelect();
	          while (results.hasNext()) {
	              
	              QuerySolution row = results.next();
	              
	                  RDFNode value = row.get("directorName");
	                  
                	  if (value != null){
	                	  if (value.isLiteral()) {
	                		  directorName = ((Literal) value).getString().replace("\n", "");
	                      } else if (value.isURIResource()) {
	                    	  directorName = ((Resource) value).getLocalName().replace("\n", "");
	                      } else {
	                    	  directorName = value.toString().replace("\n", "");
	                      }
                	  }
	                  
	              
	              sugestionStrings.add(directorName);
	          }
	          return sugestionStrings;
	      } finally {
	          qexec.close();
	      }
	}

	@Override
	protected CrudService<Director> getCrudService() {
		// TODO Auto-generated method stub
		return manageDirectorsService;
	}

	@Override
	protected Director createNewEntity() {
		// TODO Auto-generated method stub
		return new Director();
	}
	
	public ManageDirectorsController() {
	    viewPath = "/core/managedirectors/";
	    bundleName = "msgs";
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}

	public List<Director> getAllDirectors() {
		return allDirectors;
	}

	public void setAllDirectors(List<Director> allDirectors) {
		this.allDirectors = allDirectors;
	}
}
