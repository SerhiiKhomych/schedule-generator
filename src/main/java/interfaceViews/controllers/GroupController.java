package interfaceViews.controllers;

import entities.*;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.util.*;

/**
 * Created by sekh0713 on 06.01.2015.
 */
@Named("groupController")
@ConversationScoped
public class GroupController extends BasicController {
    private List<Group> groupsWithFaculties;
    private List<Faculty> faculties;
    private Group group;
    private StudyYear[] studyYears;

    @PostConstruct
    private void init() {
        this.groupsWithFaculties =  super.getDatabaseEntities().getAllGroupsWithFaculties();
        this.faculties = super.getDatabaseEntities().getAllFaculties();
        this.group = new Group();
        this.studyYears = StudyYear.values();
    }

    public List<Group> getGroupsWithFaculties() {
        return groupsWithFaculties;
    }

    public void setGroupsWithFaculties(List<Group> groupsWithFaculties) {
        this.groupsWithFaculties = groupsWithFaculties;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public StudyYear[] getStudyYears() {
        return studyYears;
    }

    public void setStudyYears(StudyYear[] studyYears) {
        this.studyYears = studyYears;
    }

    @Override
    public void removeFromCollection(Object object) {
        groupsWithFaculties.remove(object);
    }

    @Override
    public void addToCollection(Object object) {
        groupsWithFaculties.add((Group)object);
    }

    public void onRowToggle(ToggleEvent event) {
        Group changedGroup;
        if (event.getVisibility().equals(Visibility.VISIBLE)) {
            changedGroup = super.getDatabaseEntities().getGroupWithDesires((Group) event.getData());
        } else {
            changedGroup = (Group) event.getData();
            changedGroup.setDualListDesires(null);
        }
        groupsWithFaculties.set(groupsWithFaculties.indexOf(changedGroup), changedGroup);
    }
}
