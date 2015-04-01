package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergey on 27.12.2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllCorpses", query = "SELECT corps from Corps corps"),
        @NamedQuery(name = "getCorpsWithClassrooms",
                query = "SELECT corps from Corps corps JOIN FETCH corps.classrooms WHERE corps.id = ?1")
})
public class Corps implements DBEntity{
    @Id
    @GeneratedValue
    @Column(name = "corps_id")
    private Long id;
    @Column(length = 30, unique = true)
    private String name;
    @OneToMany(mappedBy = "corps", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Classroom> classrooms;

    public Corps() {
    }

    public Corps(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
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

        Corps corps = (Corps) obj;
        return this.name.equals(corps.getName());
    }
}
