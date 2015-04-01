package entities;

import org.primefaces.model.DualListModel;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergey on 27.12.2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllTeachers",
                    query = "SELECT teacher from Teacher teacher"),
        @NamedQuery(name = "getTeacherWithDesires",
                    query = "SELECT teacher from Teacher teacher JOIN FETCH teacher.desires WHERE teacher.id =?1"),
        @NamedQuery(name = "getTeacherWithStreams",
                query = "SELECT teacher from Teacher teacher JOIN FETCH teacher.streams WHERE teacher.id =?1")
})
public class Teacher implements Desirable, DBEntity{
    @Id
    @GeneratedValue
    @Column (name = "teacher_id")
    private Long id;
    @Column (length = 200, unique = true, nullable = false)
    private String fullName;
    @Column (name = "desired_workload")
    private Integer desiredWorkload;
    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "teachers_desires",
            joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "desire", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Desire> desires;
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Stream> streams;
    @Transient
    private DualListModel<Desire> dualListDesires = new DualListModel<Desire>();

    public Teacher() {
    }

    public Teacher(String fullName, Integer desiredWorkload) {
        this.fullName = fullName;
        this.desiredWorkload = desiredWorkload;
    }

    public Teacher(String fullName, Integer desiredWorkload, List<Desire> desires) {
        this.fullName = fullName;
        this.desiredWorkload = desiredWorkload;
        this.desires = desires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getDesiredWorkload() {
        return desiredWorkload;
    }

    public void setDesiredWorkload(Integer desiredWorkload) {
        this.desiredWorkload = desiredWorkload;
    }

    public List<Desire> getDesires() {
        return desires;
    }

    public void setDesires(List<Desire> desires) {
        this.desires = desires;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    public DualListModel<Desire> getDualListDesires() {
        return dualListDesires;
    }

    public void setDualListDesires(DualListModel<Desire> dualListDesires) {
        this.dualListDesires = dualListDesires;
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

        Teacher teacher = (Teacher) obj;
        return this.fullName.equals(teacher.getFullName());
    }
}
