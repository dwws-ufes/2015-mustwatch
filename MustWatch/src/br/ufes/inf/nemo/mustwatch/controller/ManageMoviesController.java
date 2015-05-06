package br.ufes.inf.nemo.mustwatch.controller;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.ufes.inf.nemo.mustwatch.application.ManageMoviesService;
import br.ufes.inf.nemo.mustwatch.application.ManageRatingsService;
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
	

	private List<Movie> movies;
	private List<Movie> movies6;
	private Movie selectedMovie;
	private String filteredName;
	private Integer rating = 3;
	private String comment;
	
	
	public Integer getRating() {
		return rating;
	}

	public void doRate(){
		if(selectedMovie != null){
			Rating rat= new Rating();
			rat.setValue(2);
			rat.setOwner(selectedMovie);
			rat.setComment("teste");
			selectedMovie.addRating(rat);
			manageMoviesService.update(selectedMovie);
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
        getLazyEntities();
        movies = getEntities();
        while(i<6 && i<movies.size()){
        	movies6.add(movies.get(i));
        	i++;
        }
        
        
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
	
	
	
	
}
