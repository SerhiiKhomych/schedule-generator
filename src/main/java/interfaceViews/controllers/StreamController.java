package interfaceViews.controllers;

import entities.*;
import interfaceViews.*;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by sekh0713 on 30.01.2015.
 */
@ManagedBean(name = "streamController")
@RequestScoped
public class StreamController extends BasicController {
    private List<Stream> streamsWithTeacherAndSubject;
    private Stream stream;

    @PostConstruct
    private void init() {
        this.streamsWithTeacherAndSubject =  super.getDatabaseEntities().getAllStreamsWithTeacherAndSubject();
        this.stream = new Stream();
    }

    public List<Stream> getStreamsWithTeacherAndSubject() {
        return streamsWithTeacherAndSubject;
    }

    public void setStreamsWithTeacherAndSubject(List<Stream> streamsWithTeacherAndSubject) {
        this.streamsWithTeacherAndSubject = streamsWithTeacherAndSubject;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    @Override
    public void removeFromCollection(Object object) {
        streamsWithTeacherAndSubject.remove((Stream)object);
    }

    @Override
    public void addToCollection(Object object) {
        streamsWithTeacherAndSubject.add((Stream)object);
    }

    public void onRowToggle(ToggleEvent event) {
        Stream changedStream;
        if (event.getVisibility().equals(Visibility.VISIBLE)) {
            changedStream = super.getDatabaseEntities().getStreamWithGroups((Stream) event.getData());
        } else {
            changedStream = (Stream) event.getData();
            changedStream.setDualListGroups(null);
        }
        streamsWithTeacherAndSubject.set(streamsWithTeacherAndSubject.indexOf(changedStream), changedStream);
    }
}
