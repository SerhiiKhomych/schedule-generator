package generateSchedule.restrictions;

import entities.*;
import generateSchedule.restrictions.RestrictionBehaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 06.02.2015.
 */
public class BasicRestrictions implements RestrictionBehaviour {
    @Override
    public boolean checkRestrictions(Classroom classroom, DayOfWeek dayOfWeek, CoupleNumber coupleNumber, Stream stream, List<Couple> couples) {
        return checkSeats(classroom, stream)
                && checkClassroomType(classroom, stream)
                && checkGroupDesires(dayOfWeek, coupleNumber, stream)
                && checkTeacherDesires(dayOfWeek, coupleNumber, stream)
                && checkClassroomDesires(dayOfWeek, coupleNumber, classroom)
                && haveGroupsCouple(dayOfWeek, coupleNumber, stream, couples)
                && haveTeacherCouple(dayOfWeek, coupleNumber, stream, couples)
                && haveGroupManyCouples(dayOfWeek, stream, couples)
                && checkFreeClassroom(dayOfWeek, coupleNumber, classroom, couples);
    }

    public boolean checkSeats(Classroom classroom, Stream stream) {
        int students = 0;
        for (Group group: stream.getGroups()) {
            students += group.getQuantity().intValue();
        }
        return classroom.getSeats().intValue() >= students;
    }

    public boolean checkClassroomType(Classroom classroom, Stream stream) {
        SubjectType classroomType = classroom.getSubjectType();
        SubjectType streamSubjectType = stream.getSubject().getSubjectType();
        return streamSubjectType.equals(classroomType) ||
                ((streamSubjectType == SubjectType.PRACTICE) && (classroomType == SubjectType.LECTURE));
    }

    public boolean checkGroupDesires(DayOfWeek dayOfWeek, CoupleNumber coupleNumber, Stream stream) {
        for (Group group : stream.getGroups()) {
            List<Desire> desires = group.getDesires();
            if (!checkDesires(dayOfWeek, coupleNumber, desires)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkTeacherDesires(DayOfWeek dayOfWeek, CoupleNumber coupleNumber, Stream stream) {
        List<Desire> desires = stream.getTeacher().getDesires();
        return checkDesires(dayOfWeek, coupleNumber, desires);
    }

    public boolean checkClassroomDesires(DayOfWeek dayOfWeek, CoupleNumber coupleNumber, Classroom classroom) {
        List<Desire> desires = classroom.getDesires();
        return checkDesires(dayOfWeek, coupleNumber, desires);
    }

    private boolean checkDesires(DayOfWeek dayOfWeek, CoupleNumber coupleNumber, List<Desire> desires) {
        for (Desire desire : desires) {
            if ((desire.getDayOfWeek().equals(dayOfWeek)) && (desire.getCoupleNumber().equals(coupleNumber))) {
                return false;
            }
        }
        return true;
    }

    public boolean haveGroupsCouple(DayOfWeek dayOfWeek, CoupleNumber coupleNumber, Stream stream, List<Couple> couples) {
        List<Group> currentGroups = stream.getGroups();
        for(Couple couple: couples) {
            if ((couple.getCoupleNumber().equals(coupleNumber)) && (couple.getDayOfWeek().equals(dayOfWeek))){
                for (Group groupOnCouple: couple.getStream().getGroups()) {
                    if (currentGroups.contains(groupOnCouple)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean haveTeacherCouple(DayOfWeek dayOfWeek, CoupleNumber coupleNumber, Stream stream, List<Couple> couples) {
        for(Couple couple: couples) {
            if ((couple.getCoupleNumber().equals(coupleNumber)) &&
                    (couple.getDayOfWeek().equals(dayOfWeek)) &&
                    (couple.getStream().getTeacher().equals(stream.getTeacher()))){
                return false;
            }
        }
        return true;
    }

    public boolean haveGroupManyCouples(DayOfWeek dayOfWeek, Stream stream, List<Couple> couples) {
        for (Group currentGroup: stream.getGroups()) {
            int couplesCount = 0;
            for(Couple couple: couples) {
                if ((couple.getDayOfWeek().equals(dayOfWeek)) &&
                        (couple.getStream().getSubject().equals(stream.getSubject())) &&
                        (couple.getStream().getGroups().contains(currentGroup))){
                    couplesCount++;
                }
                if (couplesCount == 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkFreeClassroom(DayOfWeek dayOfWeek, CoupleNumber coupleNumber, Classroom classroom, List<Couple> couples) {
        for(Couple couple: couples) {
            if ((couple.getDayOfWeek().equals(dayOfWeek)) && (couple.getCoupleNumber().equals(coupleNumber)) && (couple.getClassroom().equals(classroom))){
                return false;
            }
        }
        return true;
    }
}