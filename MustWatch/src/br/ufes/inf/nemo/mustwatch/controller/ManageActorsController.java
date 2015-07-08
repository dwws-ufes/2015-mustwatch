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

import br.ufes.inf.nemo.mustwatch.application.ManageActorsService;
import br.ufes.inf.nemo.mustwatch.domain.Actor;
import br.ufes.inf.nemo.mustwatch.domain.Director;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named("manageActorsController")
@SessionScoped
public class ManageActorsController extends CrudController<Actor>{
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	private List<Actor> allActors;

	@EJB
	ManageActorsService manageActorsService;
	
	public void init() {
		allActors = manageActorsService.getDAO().retrieveAll();
    }
	
	public List<String> getSugestions(String actor){
		
		String filme_ano1 = "select distinct ?actorName ?ano where { ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/actor>. ?x <http://data.linkedmdb.org/resource/movie/actor_name> ?actorName . filter regex(?actorName,\"";
		String filme_ano2 = "\",\"i\")}";
		Query query = QueryFactory.create(filme_ano1 +actor+filme_ano2);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		
		String actorName = null;
		
		List<String> sugestionStrings = new ArrayList<String>();
		try {
			  ResultSet results = qexec.execSelect();
	          while (results.hasNext()) {
	              
	              QuerySolution row = results.next();
	              
	                  RDFNode value = row.get("actorName");
	                  
                	  if (value != null){
	                	  if (value.isLiteral()) {
	                          actorName = ((Literal) value).getString().replace("\n", "");
	                      } else if (value.isURIResource()) {
	                    	  actorName = ((Resource) value).getLocalName().replace("\n", "");
	                      } else {
	                    	  actorName = value.toString().replace("\n", "");
	                      }
                	  }
	                  
	              
	              sugestionStrings.add(actorName);
	          }
	          return sugestionStrings;
	      } finally {
	          qexec.close();
	      }
	}
	
	

	@Override
	protected CrudService<Actor> getCrudService() {
		// TODO Auto-generated method stub
		return manageActorsService;
	}

	@Override
	protected Actor createNewEntity() {
		// TODO Auto-generated method stub
		return new Actor();
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
		
	}


	public ManageActorsController(){
		viewPath = "/core/manageactors/";
	    bundleName = "msgs";
	}

	public List<Actor> getAllActors() {
		return allActors;
	}

	public void setAllActors(List<Actor> allActors) {
		this.allActors = allActors;
	}

}
