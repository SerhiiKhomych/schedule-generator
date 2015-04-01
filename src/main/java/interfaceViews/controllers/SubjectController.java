package interfaceViews.controllers;

import entities.Subject;
import entities.SubjectType;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by sekh0713 on 06.01.2015.
 */
@Named("subjectController")
@ConversationScoped
public class SubjectController extends BasicController {
    private List<Subject> subjects;
    private Subject subject;
    private SubjectType[] subjectTypes;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectType[] getSubjectTypes() {
        return subjectTypes;
    }

    public void setSubjectTypes(SubjectType[] subjectTypes) {
        this.subjectTypes = subjectTypes;
    }

    @PostConstruct
    private void init() {
        this.subjects = super.getDatabaseEntities().getAllSubjects();
        this.subject = new Subject();
        this.subjectTypes = SubjectType.values();
    }

    @Override
    public void removeFromCollection(Object object) {
        subjects.remove(object);
    }

    @Override
    public void addToCollection(Object object) {
        subjects.add((Subject)object);
        this.subject = new Subject();
    }
}
