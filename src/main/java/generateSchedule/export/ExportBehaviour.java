package generateSchedule.export;

import entities.*;

import java.util.List;

/**
 * Created by sekh0713 on 10.02.2015.
 */
public interface ExportBehaviour {
    public void exportSchedule(List<Couple> couples, List<Faculty> faculties, StudyYear[] studyYears, DayOfWeek[] dayOfWeek, CoupleNumber[] coupleNumbers, List<Group> groups);
    public String getFilePath();
}
