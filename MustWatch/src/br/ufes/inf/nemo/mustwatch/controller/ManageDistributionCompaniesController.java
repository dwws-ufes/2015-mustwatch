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

import br.ufes.inf.nemo.mustwatch.application.ManageDistributionCompaniesService;
import br.ufes.inf.nemo.mustwatch.domain.DistributionCompany;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named("manageDistributionController")
@SessionScoped
public class ManageDistributionCompaniesController extends CrudController<DistributionCompany>{
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	private List<DistributionCompany> allDistributionCompanies;

	@EJB
	ManageDistributionCompaniesService manageDistributionCompaniesService;
	
	public void init() {
		allDistributionCompanies = manageDistributionCompaniesService.getDAO().retrieveAll();
    }
	
	public List<String> getSugestions(String company){
		
		String filme_ano1 = "select distinct ?companyName ?ano where { ?x <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://data.linkedmdb.org/resource/movie/film_distributor>. ?x <http://data.linkedmdb.org/resource/movie/film_distributor_name> ?companyName . filter regex(?companyName,\"";
		String filme_ano2 = "\",\"i\")}";
		Query query = QueryFactory.create(filme_ano1 +company+filme_ano2);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		
		String companyName = null;
		
		List<String> sugestionStrings = new ArrayList<String>();
		try {
			  ResultSet results = qexec.execSelect();
	          while (results.hasNext()) {
	              
	              QuerySolution row = results.next();
	              
	                  RDFNode value = row.get("companyName");
	                  
                	  if (value != null){
	                	  if (value.isLiteral()) {
	                          companyName = ((Literal) value).getString().replace("\n", "");
	                      } else if (value.isURIResource()) {
	                    	  companyName = ((Resource) value).getLocalName().replace("\n", "");
	                      } else {
	                    	  companyName = value.toString().replace("\n", "");
	                      }
                	  }
	                  
	              
	              sugestionStrings.add(companyName);
	          }
	          return sugestionStrings;
	      } finally {
	          qexec.close();
	      }
	}

	public List<DistributionCompany> getAllDistributionCompanies() {
		return allDistributionCompanies;
	}

	public void setAllDistributionCompanies(List<DistributionCompany> allDistributionCompanies) {
		this.allDistributionCompanies = allDistributionCompanies;
	}

	@Override
	protected CrudService<DistributionCompany> getCrudService() {
		return manageDistributionCompaniesService;
	}

	@Override
	protected DistributionCompany createNewEntity() {
		return new DistributionCompany();
	}

	@Override
	protected void initFilters() {
		
	}


	public ManageDistributionCompaniesController(){
		viewPath = "/core/manageDistributionCompanies/";
	    bundleName = "msgs";
	}


}
