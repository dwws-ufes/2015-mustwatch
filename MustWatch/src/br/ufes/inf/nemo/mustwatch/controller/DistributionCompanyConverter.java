package br.ufes.inf.nemo.mustwatch.controller;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

import br.ufes.inf.nemo.mustwatch.application.ManageDistributionCompaniesService;
import br.ufes.inf.nemo.mustwatch.domain.DistributionCompany;
 

@Named("distributionCompanyConverter")
@SessionScoped
public class DistributionCompanyConverter implements Serializable, Converter {
 
	@EJB
    private ManageDistributionCompaniesService manageDistributionCompaniesService;
	
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                
                return manageDistributionCompaniesService.getDAO().retrieveById(new Long(Integer.parseInt(value)));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid distribution company."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((DistributionCompany) object).getId());
        }
        else {
            return null;
        }
    }   
} 

