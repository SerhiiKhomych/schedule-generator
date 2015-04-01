package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergey on 28.12.2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getDepartmentByShortNameAndLongName",
                    query = "SELECT dep from Department dep WHERE dep.shortName = ?1 and dep.longName = ?2"),
        @NamedQuery(name = "getAllDepartments",
                    query = "SELECT dep from Department dep"),
        @NamedQuery(name = "getDepartmentWithFaculties",
                query = "SELECT dep from Department dep JOIN FETCH dep.faculties WHERE dep.id =?1")
})
public class Department implements DBEntity{
    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private Long id;
    @Column(length = 80, unique = true, nullable = false)
    private String shortName;
    @Column(length = 200)
    private String longName;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Faculty> faculties;
    public Department() {
    }

    public Department(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
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

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
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

        Department department = (Department) obj;
        return this.shortName.equals(department.getShortName());
    }
}
