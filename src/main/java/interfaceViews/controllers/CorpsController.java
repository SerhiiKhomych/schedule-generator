package interfaceViews.controllers;

import entities.Corps;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by sekh0713 on 06.01.2015.
 */
@Named("corpsController")
@ConversationScoped
public class CorpsController extends BasicController {
    private List<Corps> corpses;
    private Corps corps;

    @PostConstruct
    private void init() {
        this.corpses = super.getDatabaseEntities().getAllCorpses();
        this.corps = new Corps();
    }

    public List<Corps> getCorpses() {
        return corpses;
    }

    public void setCorpses(List<Corps> corpses) {
        this.corpses = corpses;
    }

    public Corps getCorps() {
        return corps;
    }

    public void setCorps(Corps corps) {
        this.corps = corps;
    }

    @Override
    public void removeFromCollection(Object object) {
        corpses.remove(object);
    }

    @Override
    public void addToCollection(Object object) {
        corpses.add((Corps) object);
        this.corps = new Corps();
    }
}
