package generateSchedule.initializing;

import entities.*;
import generateSchedule.ScheduleStatus;
import generateSchedule.restrictions.RestrictionBehaviour;

import javax.enterprise.event.Event;
import java.util.List;

/**
 * Created by Sergey on 07.02.2015.
 */
public interface InitialApproximationBehaviour {
    public List<Couple> initialApproximateSchedule(List<Classroom> classrooms,
                                CoupleNumber[] coupleNumbers,
                                DayOfWeek[] dayOfWeeks,
                                List<Stream> streams,
                                RestrictionBehaviour restrictionBehaviour,
                                ScheduleStatus scheduleStatus,
                                Event<ScheduleStatus> scheduleStatusEvent);
}
