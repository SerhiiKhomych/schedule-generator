package entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Sergey on 28.12.2014.
 */
@Entity
public class SubGroup extends Group {
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Group parentGroup;

    public SubGroup() {
    }

    public SubGroup(Faculty faculty, StudyYear studyYear, String name, Integer quantity) {
        super(faculty, studyYear, name, quantity);
    }

    public Group getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }
}
