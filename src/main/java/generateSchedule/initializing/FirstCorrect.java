package generateSchedule.initializing;

import entities.*;
import generateSchedule.ScheduleStatus;
import generateSchedule.initializing.InitialApproximationBehaviour;
import generateSchedule.restrictions.RestrictionBehaviour;

import javax.enterprise.event.Event;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sergey on 07.02.2015.
 */
public class FirstCorrect implements InitialApproximationBehaviour {
    @Override
    public List<Couple> initialApproximateSchedule(List<Classroom> classrooms,
                                    CoupleNumber[] coupleNumbers,
                                    DayOfWeek[] dayOfWeeks,
                                    List<Stream> streams,
                                    RestrictionBehaviour restrictions,
                                    ScheduleStatus scheduleStatus,
                                    Event<ScheduleStatus> scheduleStatusEvent) {
        List<Couple> couples = new ArrayList<Couple>();
        boolean flag = false;
        int iterations = 0;
        scheduleStatus.setGenerating(true);
        Collections.reverse(streams);
        Collections.reverse(classrooms);
        Arrays.sort(dayOfWeeks);
        Arrays.sort(coupleNumbers);
        for(Stream stream: streams) {
            flag = false;
            for(Classroom classroom: classrooms) {
                if (flag) break;
                for (DayOfWeek dayOfWeek: dayOfWeeks) {
                    if (flag) break;
                    for (CoupleNumber coupleNumber: coupleNumbers) {
                        if (flag) break;
                        if (restrictions.checkRestrictions(classroom, dayOfWeek, coupleNumber, stream, couples)){
                            couples.add(new Couple(classroom, coupleNumber, dayOfWeek, stream));
                            flag = true;
                        }
                    }
                }
            }
            scheduleStatus.setCurrentIteration(iterations++);
            scheduleStatusEvent.fire(scheduleStatus);
        }
        scheduleStatus.setGenerating(false);
        return couples;
    }
}
