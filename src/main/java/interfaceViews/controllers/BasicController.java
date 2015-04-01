package interfaceViews.controllers;

import ejb.DatabaseEntities;
import entities.Desirable;
import entities.Desire;
import entities.Group;
import entities.Stream;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sekh0713 on 06.01.2015.
 */
public abstract class BasicController implements Serializable{
    @EJB
    private DatabaseEntities databaseEntities;

    public DatabaseEntities getDatabaseEntities() {
        return databaseEntities;
    }

    abstract public void removeFromCollection(Object object);

    abstract public void addToCollection(Object object);

    public void addRow(Object object) {
        databaseEntities.createEntity(object);
        addToCollection(object);
        FacesMessage msg = new FacesMessage("Додано");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        databaseEntities.updateEntity(event.getObject());
        FacesMessage msg = new FacesMessage("Змінено");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        databaseEntities.deleteEntity(event.getObject());
        removeFromCollection(event.getObject());
        FacesMessage msg = new FacesMessage("Видалено");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void updateDesires(Object object) {
        List<Desire> doNotWantDesires = ((Desirable) object).getDualListDesires().getTarget();
        ((Desirable) object).setDesires(doNotWantDesires);
        databaseEntities.updateEntity(object);
        FacesMessage msg = new FacesMessage("Змінено");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void updateGroups(Object object) {
        List<Group> groupOnStream = ((Stream)object).getDualListGroups().getSource();
        ((Stream)object).setGroups(groupOnStream);
        databaseEntities.updateEntity(object);
        FacesMessage msg = new FacesMessage("Змінено");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
