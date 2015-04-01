package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergey on 28.12.2014.
 */
@Entity
@NamedQueries({
     @NamedQuery(name = "getFacultyByShortNameAndLongNameAndFaculty",
                 query = "SELECT fac from Faculty fac WHERE fac.shortName = ?1 and fac.longName = ?2 and fac.department = ?3"),
     @NamedQuery(name = "getAllFaculties",
                 query = "SELECT fac from Faculty fac"),
     @NamedQuery(name = "getAllFacultiesWithDepartments",
                 query = "SELECT fac from Faculty fac JOIN FETCH fac.department"),
     @NamedQuery(name = "getFacultyWithGroups",
                query = "SELECT fac from Faculty fac JOIN FETCH fac.groups WHERE fac.id =?1")
})
@Table(indexes = {
        @Index(name = "department_fk_index", columnList = "department_id")})
public class Faculty implements DBEntity{
    @Id
    @GeneratedValue
    @Column(name = "faculty_id")
    private Long id;
    @Column(length = 80, unique = true, nullable = false)
    private String shortName;
    @Column(length = 200)
    private String longName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Group> groups;

    public Faculty() {
    }

    public Faculty(String shortName, String longName, Department department) {
        this.shortName = shortName;
        this.longName = longName;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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

        Faculty faculty = (Faculty) obj;
        return this.shortName.equals(faculty.getShortName());
    }
}
