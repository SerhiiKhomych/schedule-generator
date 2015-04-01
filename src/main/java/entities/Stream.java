package entities;

import org.primefaces.model.DualListModel;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergey on 28.12.2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllStreamsWithTeacherAndSubject",
                query = "SELECT stream from Stream stream JOIN FETCH stream.teacher JOIN FETCH stream.subject"),
        @NamedQuery(name = "getStreamWithGroups",
                query = "SELECT stream from Stream stream JOIN FETCH stream.groups WHERE stream.id =?1")
})
public class Stream implements DBEntity, Comparable<Stream>{
    @Id
    @GeneratedValue
    @Column(name = "stream_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "groups_on_stream",
        joinColumns = @JoinColumn(name = "stream_id"),
        inverseJoinColumns= @JoinColumn(name = "group_id"))
    private List<Group> groups;
    @Column
    private Integer hours;
    @Transient
    private DualListModel<Group> dualListGroups;

    public Stream() {
    }

    public Stream(Teacher teacher, Subject subject, List<Group> groups, Integer hours) {
        this.teacher = teacher;
        this.subject = subject;
        this.groups = groups;
        this.hours = hours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public DualListModel<Group> getDualListGroups() {
        return dualListGroups;
    }

    public void setDualListGroups(DualListModel<Group> dualListGroups) {
        this.dualListGroups = dualListGroups;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Stream stream = (Stream) obj;
        return id.equals(stream.getId());
    }

    @Override
    public int compareTo(Stream stream) {
        Integer studentsCount = 0;
        Integer streamStudentsCount = 0;
        for (Group group: groups) {
            studentsCount += group.getQuantity();
        }
        for (Group group: stream.getGroups()) {
            streamStudentsCount += group.getQuantity();
        }
        return studentsCount.compareTo(streamStudentsCount);
    }
}
