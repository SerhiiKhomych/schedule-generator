package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergey on 27.12.2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllSubjects", query = "SELECT subj from Subject subj"),
        @NamedQuery(name = "getSubjectWithStreams",
                    query = "SELECT subj from Subject subj JOIN FETCH subj.streams WHERE subj.id =?1")
})
public class Subject implements DBEntity{
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type")
    private SubjectType subjectType;
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Stream> streams;
    public Subject() {
    }

    public Subject(String name, SubjectType subjectType) {
        this.name = name;
        this.subjectType = subjectType;
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

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Subject))
            return false;
        if (obj == this)
            return true;

        Subject subject = (Subject) obj;
        return new EqualsBuilder()
                .append(name, subject.getName())
                .append(subjectType, subject.getSubjectType())
                .isEquals();
    }
}
