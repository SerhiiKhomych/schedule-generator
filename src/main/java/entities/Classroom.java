package entities;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.primefaces.model.DualListModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergey on 27.12.2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllClassrooms", query = "SELECT classroom from Classroom classroom"),
        @NamedQuery(name = "getAllClassroomsWithCorpses",
                    query = "SELECT classroom from Classroom classroom JOIN FETCH classroom.corps"),
        @NamedQuery(name = "getClassroomWithDesires",
                query = "SELECT classroom from Classroom classroom JOIN FETCH classroom.desires WHERE classroom.id =?1"),
        @NamedQuery(name = "getAllClassroomsWithCorpsesAndDesires",
                query = "SELECT classroom from Classroom classroom JOIN FETCH classroom.corps LEFT JOIN FETCH classroom.desires")
})
public class Classroom implements Desirable, DBEntity, Comparable<Classroom>{
    @Id
    @GeneratedValue
    @Column(name = "classroom_id")
    private Long id;
    @Column
    private Integer seats;
    @Column(length = 50, unique = true, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type")
    private SubjectType subjectType;
    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "corps_id")
    private Corps corps;
    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "classrooms_desires",
            joinColumns = @JoinColumn(name = "classroom_id"))
    @Column(name = "desire", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Desire> desires;
    @Transient
    private DualListModel<Desire> dualListDesires = new DualListModel<Desire>();;

    public Classroom() {
    }

    public Classroom(Integer seats, String name, SubjectType subjectType, Corps corps) {
        this.seats = seats;
        this.name = name;
        this.subjectType = subjectType;
        this.corps = corps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public Corps getCorps() {
        return corps;
    }

    public void setCorps(Corps corps) {
        this.corps = corps;
    }

    public List<Desire> getDesires() {
        return desires;
    }

    public void setDesires(List<Desire> desires) {
        this.desires = desires;
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

        Classroom classroom = (Classroom) obj;
        return this.name.equals(classroom.getName());
    }

    @Override
    public int compareTo(Classroom classroom) {
        return seats.compareTo(classroom.seats);
    }
}
