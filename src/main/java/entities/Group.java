package entities;

import org.primefaces.model.DualListModel;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 28.12.2014.
 */
@Entity (name = "Groups")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
        @NamedQuery(name = "getGroupsByFaculty",
                    query = "SELECT gr from Groups gr WHERE gr.faculty = ?1"),
        @NamedQuery(name = "getAllGroups",
                    query = "SELECT gr from Groups gr"),
        @NamedQuery(name = "getAllGroupsWithFaculties",
                    query = "SELECT gr from Groups gr JOIN FETCH gr.faculty"),
        @NamedQuery(name = "getGroupWithDesires",
                    query = "SELECT gr from Groups gr JOIN FETCH gr.desires WHERE gr.id =?1"),
        @NamedQuery(name = "getGroupWithStream",
                    query = "SELECT gr from Groups gr JOIN FETCH gr.streams WHERE gr.id =?1")
})
@Table(indexes = {
        @Index(name = "faculty_fk_index", columnList = "faculty_id")
})
public class Group implements Desirable, DBEntity{
    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @Enumerated(EnumType.STRING)
    @Column(name = "study_year")
    private StudyYear studyYear;
    @Column(length = 80, unique = true, nullable = false)
    private String name;
    @Column
    private Integer quantity;
    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "groups_desires",
            joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "desire", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Desire> desires;
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Stream> streams;
    @Transient
    private DualListModel<Desire> dualListDesires = new DualListModel<Desire>();

    public Group() {
    }

    public Group(Faculty faculty, StudyYear studyYear, String name, Integer quantity) {
        this.faculty = faculty;
        this.studyYear = studyYear;
        this.name = name;
        this.quantity = quantity;
    }

    public Group(Faculty faculty, StudyYear studyYear, String name, Integer quantity, List<Desire> desires) {
        this.faculty = faculty;
        this.studyYear = studyYear;
        this.name = name;
        this.quantity = quantity;
        this.desires = desires;
    }

    public Group(Faculty faculty, StudyYear studyYear, String name, Integer quantity, List<Desire> desires, DualListModel<Desire> dualListDesires) {
        this.faculty = faculty;
        this.studyYear = studyYear;
        this.name = name;
        this.quantity = quantity;
        this.desires = desires;
        this.dualListDesires = dualListDesires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    public static List<Group> getGroupsByStudyYearAndFaculty(List<Group> allGroups, StudyYear studyYear, Faculty faculty) {
        List<Group> groupsByStudyYear = new ArrayList<Group>();
        for(Group group: allGroups) {
            if ((group.getStudyYear().equals(studyYear)) && (group.getFaculty().equals(faculty))) {
                groupsByStudyYear.add(group);
            }
        }
        return groupsByStudyYear;
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

        Group group = (Group) obj;
        return group.name.equals(group.getName());
    }
}
