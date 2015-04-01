package generateSchedule.restrictions;

import entities.*;

import java.util.List;

/**
 * Created by Sergey on 07.02.2015.
 */
public interface RestrictionBehaviour {
    public boolean checkRestrictions(Classroom classroom, DayOfWeek dayOfWeek, CoupleNumber coupleNumber, Stream stream, List<Couple> couples);
}
