package br.ufes.inf.nemo.mustwatch.controller;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageDirectorsService;
import br.ufes.inf.nemo.mustwatch.domain.Director;
import java.io.Serializable;
 

@Named("directorConverter")
@SessionScoped
public class DirectorConverter implements Serializable, Converter {
 
	@EJB
    private ManageDirectorsService manageDirectorsController;
	
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                
                return manageDirectorsController.getDAO().retrieveById(new Long(Integer.parseInt(value)));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid director."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Director) object).getId());
        }
        else {
            return null;
        }
    }   
} 

