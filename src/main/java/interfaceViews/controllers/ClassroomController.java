package interfaceViews.controllers;

import entities.Classroom;
import entities.SubjectType;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by sekh0713 on 06.01.2015.
 */
@Named("classroomController")
@ConversationScoped
public class ClassroomController extends BasicController {
    private List<Classroom> classroomsWithCorpses;
    private Classroom classroom;
    private SubjectType[] subjectTypes;

    @PostConstruct
    private void init() {
        this.classroomsWithCorpses = super.getDatabaseEntities().getAllClassroomsWithCorpses();
        this.classroom = new Classroom();
        this.subjectTypes = SubjectType.values();
    }

    public List<Classroom> getClassroomsWithCorpses() {
        return classroomsWithCorpses;
    }

    public void setClassroomsWithCorpses(List<Classroom> classroomsWithCorpses) {
        this.classroomsWithCorpses = classroomsWithCorpses;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public SubjectType[] getSubjectTypes() {
        return subjectTypes;
    }

    public void setSubjectTypes(SubjectType[] subjectTypes) {
        this.subjectTypes = subjectTypes;
    }

    @Override
    public void removeFromCollection(Object object) {
        classroomsWithCorpses.remove(object);
    }

    @Override
    public void addToCollection(Object object) {
        classroomsWithCorpses.add((Classroom) object);
    }

    public void onRowToggle(ToggleEvent event) {
        Classroom changedClassroom;
        if (event.getVisibility().equals(Visibility.VISIBLE)) {
            changedClassroom = super.getDatabaseEntities().getClassroomWithDesires((Classroom) event.getData());
        } else {
            changedClassroom = (Classroom) event.getData();
            changedClassroom.setDualListDesires(null);
        }
        classroomsWithCorpses.set(classroomsWithCorpses.indexOf(changedClassroom), changedClassroom);
    }
}
