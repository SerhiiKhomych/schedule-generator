package interfaceViews.controllers;

import entities.Teacher;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by sekh0713 on 06.01.2015.
 */
@Named("teacherController")
@ConversationScoped
public class TeacherController extends BasicController {
    private List<Teacher> teachers;
    private Teacher teacher;

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @PostConstruct
    private void init() {
        this.teachers = super.getDatabaseEntities().getAllTeachers();
        this.teacher = new Teacher();
    }

    @Override
    public void removeFromCollection(Object object) {
        teachers.remove((Teacher)object);
    }

    @Override
    public void addToCollection(Object object) {
        teachers.add((Teacher)object);
    }

    public void onRowToggle(ToggleEvent event) {
        Teacher changedTeacher;
        if (event.getVisibility().equals(Visibility.VISIBLE)) {
            changedTeacher = super.getDatabaseEntities().getTeacherWithDesires((Teacher) event.getData());
        } else {
            changedTeacher = (Teacher) event.getData();
            changedTeacher.setDualListDesires(null);
        }
        teachers.set(teachers.indexOf(changedTeacher), changedTeacher);
    }
}
