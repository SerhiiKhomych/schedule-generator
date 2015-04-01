package interfaceViews.converters;

import ejb.DatabaseEntities;
import entities.Corps;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by sekh0713 on 26.01.2015.
 */
@FacesConverter("corpsConverter")
@ManagedBean
@RequestScoped
public class CorpsConverter implements Converter {
    @EJB
    DatabaseEntities entities;
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return entities.getCorpsById(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return Long.toString(((Corps) o).getId());
    }
}
