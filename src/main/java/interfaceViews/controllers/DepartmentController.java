package interfaceViews.controllers;

import entities.Department;
import javax.annotation.PostConstruct;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
/**
 * Created by sekh0713 on 06.01.2015.
 */
@Named("departmentController")
@ConversationScoped
public class DepartmentController extends BasicController {
    private List<Department> departments;
    private Department department;

    @PostConstruct
    private void init() {
        this.departments = super.getDatabaseEntities().getAllDepartments();
        this.department = new Department();
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public void addToCollection(Object object) {
        departments.add((Department)object);
        this.department = new Department();
    }

    @Override
    public void removeFromCollection(Object object) {
        departments.remove(object);
    }
}
