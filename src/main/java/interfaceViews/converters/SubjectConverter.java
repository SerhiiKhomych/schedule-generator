package interfaceViews.converters;

import ejb.DatabaseEntities;
import entities.Subject;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by sekh0713 on 04.02.2015.
 */
@FacesConverter("subjectConverter")
@ManagedBean
@RequestScoped
public class SubjectConverter implements Converter {
    @EJB
    DatabaseEntities entities;
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return entities.getSubjectById(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return Long.toString(((Subject) o).getId());
    }
}
