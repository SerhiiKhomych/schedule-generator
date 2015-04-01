package ejb;

import entities.*;
import org.hibernate.Hibernate;
import org.primefaces.model.DualListModel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sergey on 27.12.2014.
 */
@Stateless
public class DatabaseEntities {
    @PersistenceContext(unitName = "OracleDB")
    private EntityManager em;

    // CRUD fot entities
    public void createEntity(Object object) {
        if (object instanceof List) {
            for (Object obj : (List)object) {
                em.persist(obj);
            }
        } else {
            em.persist(object);
        }
    }
    public void updateEntity(Object object) {
        if (object instanceof List) {
            for (Object obj : (List)object) {
                obj = (em.contains(obj)) ? obj : em.merge(obj);
                em.merge(obj);
            }
        } else {
            object = (em.contains(object)) ? object : em.merge(object);
            em.merge(object);
        }
    }
    public void deleteEntity(Object object) {
        if (object instanceof List) {
            for (Object obj : (List)object) {
                delete(obj);
            }
        } else {
            delete(object);
        }
    }

    private void delete(Object object) {
        if (object instanceof Department) {
            deleteDepartment((Department)object);
        } else if (object instanceof Faculty) {
            deleteFaculty((Faculty)object);
        } else if (object instanceof Group) {
            deleteGroup((Group)object);
        } else if (object instanceof Teacher) {
            deleteTeacher((Teacher)object);
        } else if (object instanceof Subject) {
            deleteSubject((Subject)object);
        } else if (object instanceof Corps) {
            deleteCorps((Corps)object);
        } else if (object instanceof Classroom) {
            deleteClassroom((Classroom)object);
        } else if (object instanceof Stream) {
            deleteStream((Stream)object);
        } else if(object instanceof Couple) {
            deleteCouple((Couple)object);
        }
    }

    // Selects for Departments
    public Department getDepartmentById(String id) {
        return em.find(Department.class, Long.valueOf(id));
    }
    public List<Department> getAllDepartments() {
        return em.createNamedQuery("getAllDepartments", Department.class)
                .getResultList();
    }
    public Department getDepartmentWithFaculties(Department department) {
        try {
            return em.createNamedQuery("getDepartmentWithFaculties", Department.class)
                    .setParameter(1, department.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            department.setFaculties(Collections.EMPTY_LIST);
            return department;
        }
    }
    // Deleting Department
    private void deleteDepartment(Department department) {
        department = getDepartmentWithFaculties(department);
        for (Faculty faculty : department.getFaculties()) {
            faculty = getFacultyWithGroups(faculty);
            for (Group group : faculty.getGroups()) {
                deleteGroupOnStreams(group);
            }
        }

        em.remove((em.contains(department)) ? department : em.merge(department));
    }

    // Selects for Faculties
    public Faculty getFacultyById(String id) {
        return em.find(Faculty.class, Long.valueOf(id));
    }
    public List<Faculty> getAllFaculties() {
        return em.createNamedQuery("getAllFaculties", Faculty.class)
                .getResultList();
    }
    public List<Faculty> getAllFacultiesWithDepartments() {
        return em.createNamedQuery("getAllFacultiesWithDepartments", Faculty.class)
                .getResultList();
    }
    public Faculty getFacultyWithGroups(Faculty faculty) {
        try {
            return em.createNamedQuery("getFacultyWithGroups", Faculty.class)
                    .setParameter(1, faculty.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            faculty.setGroups(Collections.EMPTY_LIST);
            return faculty;
        }
    }
    // Deleting Faculty
    private void deleteFaculty(Faculty faculty) {
        faculty = getFacultyWithGroups(faculty);
        for (Group group : faculty.getGroups()) {
            deleteGroupOnStreams(group);
        }
        em.remove((em.contains(faculty)) ? faculty : em.merge(faculty));
    }

    // Selects for Groups
    public Group getGroupById(String id) {
        return em.find(Group.class, Long.valueOf(id));
    }
    public List<Group> getAllGroups() {
        return em.createNamedQuery("getAllGroups", Group.class)
                .getResultList();
    }
    public List<Group> getAllGroupsWithFaculties() {
        return em.createNamedQuery("getAllGroupsWithFaculties", Group.class)
                .getResultList();
    }
    public Group getGroupWithStream(Group group) {
        try{
            return em.createNamedQuery("getGroupWithStream", Group.class)
                    .setParameter(1, group.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            group.setStreams(Collections.EMPTY_LIST);
            return group;
        }
    }
    public Group getGroupWithDesires(Group group) {
        try {
            group =  em.createNamedQuery("getGroupWithDesires", Group.class)
                    .setParameter(1, group.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            group.setDesires(Collections.EMPTY_LIST);
        }
        return (Group)initializeDualDesires(group);
    }
    // Deleting Group
    private void deleteGroup(Group group) {
        deleteGroupOnStreams(group);
        group = getGroupWithDesires(group);
        em.remove((em.contains(group)) ? group : em.merge(group));
    }

    // Selects for Teachers
    public Teacher getTeacherById(String id) {
        return em.find(Teacher.class, Long.valueOf(id));
    }
    public List<Teacher> getAllTeachers() {
        return em.createNamedQuery("getAllTeachers", Teacher.class)
                .getResultList();
    }
    public Teacher getTeacherWithDesires(Teacher teacher) {
        try {
            teacher =  em.createNamedQuery("getTeacherWithDesires", Teacher.class)
                    .setParameter(1, teacher.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            teacher.setDesires(Collections.EMPTY_LIST);
        }
        return (Teacher)initializeDualDesires(teacher);
    }
    public Teacher getTeacherWithStreams(Teacher teacher) {
        try {
            return em.createNamedQuery("getTeacherWithStreams", Teacher.class)
                    .setParameter(1, teacher.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            teacher.setStreams(Collections.EMPTY_LIST);
            return teacher;
        }
    }
    // Deleting Teacher
    private void deleteTeacher(Teacher teacher) {
        deleteStreamsWithTeacher(teacher);
        teacher = getTeacherWithDesires(teacher);
        em.remove((em.contains(teacher)) ? teacher : em.merge(teacher));
    }

    // Selects for Subjects
    public Subject getSubjectById(String id) {
        return em.find(Subject.class, Long.valueOf(id));
    }
    public List<Subject> getAllSubjects() {
        return em.createNamedQuery("getAllSubjects", Subject.class)
                .getResultList();
    }
    public Subject getSubjectWithStreams(Subject subject) {
        try {
            return em.createNamedQuery("getSubjectWithStreams", Subject.class)
                    .setParameter(1, subject.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            subject.setStreams(Collections.EMPTY_LIST);
            return subject;
        }
    }
    // Deleting Subject
    private void deleteSubject(Subject subject) {
        deleteStreamsWithSubject(subject);
        em.remove((em.contains(subject)) ? subject : em.merge(subject));
    }

    // Selects for Corpses
    public Corps getCorpsById(String id) {
        return em.find(Corps.class, Long.valueOf(id));
    }
    public List<Corps> getAllCorpses() {
        return em.createNamedQuery("getAllCorpses", Corps.class)
                .getResultList();
    }
    public Corps getCorpsWithClassrooms(Corps corps) {
        try{
            return em.createNamedQuery("getCorpsWithClassrooms", Corps.class)
                    .setParameter(1, corps.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            corps.setClassrooms(Collections.EMPTY_LIST);
            return corps;
        }
    }
    // Deleting Corps
    private void deleteCorps(Corps corps) {
        corps = getCorpsWithClassrooms(corps);
        for (Classroom classroom: corps.getClassrooms()) {
            classroom = getClassroomWithDesires(classroom);
        }
        em.remove((em.contains(corps)) ? corps : em.merge(corps));
    }

    // Selects for Classrooms
    public List<Classroom> getAllClassroomsWithCorpses() {
        return em.createNamedQuery("getAllClassroomsWithCorpses", Classroom.class)
                .getResultList();
    }
    public Classroom getClassroomWithDesires(Classroom classroom) {
        try {
            classroom =  em.createNamedQuery("getClassroomWithDesires", Classroom.class)
                    .setParameter(1, classroom.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            classroom.setDesires(Collections.EMPTY_LIST);
        }
        return (Classroom)initializeDualDesires(classroom);
    }
    public List<Classroom> getAllClassroomsWithCorpsesAndDesires() {
        return em.createNamedQuery("getAllClassroomsWithCorpsesAndDesires", Classroom.class)
                .getResultList();
    }
    // Deleting Classroom
    private void deleteClassroom(Classroom classroom) {
        classroom = getClassroomWithDesires(classroom);
        em.remove((em.contains(classroom)) ? classroom : em.merge(classroom));
    }

    // Selects for Streams
    public List<Stream> getAllStreamsWithTeacherAndSubject() {
        return em.createNamedQuery("getAllStreamsWithTeacherAndSubject", Stream.class)
                .getResultList();
    }
    public Stream getStreamWithGroups(Stream stream) {
        try {
            stream =  em.createNamedQuery("getStreamWithGroups", Stream.class)
                    .setParameter(1, stream.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            stream.setGroups(Collections.EMPTY_LIST);
        }
        return initializeDualGroupList(stream);
    }
    public List<Stream> initializeStreams(List<Stream> streams) {
        List<Stream> initializedStreams = new ArrayList<>();
        for(Stream stream: streams){
            Stream initializedStream = em.find(Stream.class, stream.getId());
            Teacher initializedTeacher = em.find(Teacher.class, stream.getTeacher().getId());
            List<Group> initializedGroups = new ArrayList<>();
            for(Group group: stream.getGroups()) {
                Group newGroup = em.find(Group.class, group.getId());
                Hibernate.initialize(newGroup.getDesires());
                initializedGroups.add(newGroup);
            }
            Hibernate.initialize(initializedTeacher.getDesires());
            Hibernate.initialize(initializedStream.getGroups());

            initializedStream.setGroups(initializedGroups);
            initializedStream.setTeacher(initializedTeacher);
            initializedStreams.add(initializedStream);
        }
        return initializedStreams;
    }
    // Deleting Stream
    private void deleteStream(Stream stream) {
        em.remove((em.contains(stream)) ? stream : em.merge(stream));
    }
    private void deleteGroupOnStreams(Group group) {
        group = getGroupWithStream(group);
        for (Stream stream : group.getStreams()) {
            stream.getGroups().remove(group);
            updateEntity(stream);
        }
    }
    private void deleteStreamsWithTeacher(Teacher teacher) {
        teacher = getTeacherWithStreams(teacher);
        for (Stream stream : teacher.getStreams()) {
            em.remove(stream);
        }
    }
    private void deleteStreamsWithSubject(Subject subject) {
        subject = getSubjectWithStreams(subject);
        for (Stream stream : subject.getStreams()) {
            em.remove(stream);
        }
    }

    // Selects for Couples
    public List<Couple> getAllCouples() {
        return em.createNamedQuery("getAllCouples", Couple.class)
                .getResultList();
    }
    // Deleting Couple
    private void deleteCouple(Couple couple) {
        em.remove((em.contains(couple)) ? couple : em.merge(couple));
    }

    // Initializing DualLists
    public Desirable initializeDualDesires(Desirable desirableObject) {
        List<Desire> doNotWantDesires = desirableObject.getDesires();
        List<Desire> wantDesires = Arrays.asList(Desire.values());
        wantDesires.removeAll(doNotWantDesires);
        desirableObject.setDualListDesires(new DualListModel<>(wantDesires, doNotWantDesires));
        return desirableObject;
    }

    public Stream initializeDualGroupList(Stream stream) {
        List<Group> streamGroups = stream.getGroups();
        List<Group> doNotExistsGroups = getAllGroups();
        doNotExistsGroups.removeAll(streamGroups);

        stream.setDualListGroups(new DualListModel<>(streamGroups, doNotExistsGroups));
        return stream;
    }
}

