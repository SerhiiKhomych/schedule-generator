package interfaceViews.controllers;

import entities.Faculty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by sekh0713 on 06.01.2015.
 */
@Named("facultyController")
@ConversationScoped
public class FacultyController extends BasicController {
    private List<Faculty> facultiesWithDepartments;
    private Faculty faculty;

    @PostConstruct
    private void init() {
        this.facultiesWithDepartments = super.getDatabaseEntities().getAllFacultiesWithDepartments();
        this.faculty = new Faculty();
    }

    public List<Faculty> getFacultiesWithDepartments() {
        return facultiesWithDepartments;
    }

    public void setFacultiesWithDepartments(List<Faculty> facultiesWithDepartments) {
        this.facultiesWithDepartments = facultiesWithDepartments;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public void removeFromCollection(Object object) {
        facultiesWithDepartments.remove(object);
    }

    @Override
    public void addToCollection(Object object) {
        facultiesWithDepartments.add((Faculty)object);
        this.faculty = new Faculty();
    }
}