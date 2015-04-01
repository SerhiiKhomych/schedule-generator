package interfaceViews.converters;

import ejb.DatabaseEntities;
import entities.Faculty;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by Sergey on 31.12.2014.
 */
@FacesConverter("facultyConverter")
@ManagedBean
@RequestScoped
public class FacultyConverter implements Converter {
    @EJB
    DatabaseEntities entities;
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return entities.getFacultyById(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return Long.toString(((Faculty) o).getId());
    }
}