import ejb.DatabaseEntities;
import entities.Department;
import entities.Faculty;
import entities.Group;
import entities.StudyYear;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sergey on 28.12.2014.
 */
public class EJBEntitiesTest extends EJBLocal {
    private DatabaseEntities entities;

    public EJBEntitiesTest() {
        this.entities = super.getEntities();
    }

    @Test
    public void addDepartment() {
    /*
        Department department = new Department("test", "test1");
        entities.createEntity(department);
        Assert.assertEquals(entities.getDepartmentByShortNameAndLongName("test", "test1"),department);
     */
    }

    @Test
    public void addFaculty() {
    /*
        Department department = new Department("test", "test1");
        Faculty faculty = new Faculty("test", "test1", department);
        entities.createEntity(department);
        entities.createEntity(faculty);
        Assert.assertEquals(entities.getFacultyByShortNameAndLongNameAndFaculty("test", "test1", department), faculty);
    */
    }

    @Test
    public void addGroup() {
    /*
        Department department = new Department("test", "test1");
        Faculty faculty = new Faculty("test", "test1", department);
        StudyYear studyYear = StudyYear.FIRST;
        Group group = new Group(faculty, studyYear, "test1", new Integer(1));
        entities.createEntity(department);
        entities.createEntity(faculty);
        entities.createEntity(group);
        Assert.assertEquals(entities.getGroupsByFaculty(faculty).get(0), group);
    */
    }
}
