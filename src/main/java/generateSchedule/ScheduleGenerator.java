package generateSchedule;

import ejb.DatabaseEntities;
import entities.*;
import generateSchedule.export.ExportBehaviour;
import generateSchedule.improving.ImprovingBehaviour;
import generateSchedule.initializing.InitialApproximationBehaviour;
import generateSchedule.restrictions.RestrictionBehaviour;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 07.02.2015.
 */
public abstract class ScheduleGenerator {
    private ExportBehaviour exportBehaviour;
    private ImprovingBehaviour improvingBehaviour;
    private InitialApproximationBehaviour initialApproximationBehaviour;
    private RestrictionBehaviour restrictionBehaviour;

    private List<Classroom> classrooms;
    private CoupleNumber[] coupleNumbers;
    private DayOfWeek[] dayOfWeeks;
    private List<Stream> streams;
    private List<Faculty> faculties;
    private List<Group> groups;
    private StudyYear[] studyYears;
    private ScheduleStatus scheduleStatus;
    @EJB
    private DatabaseEntities entities;
    @Inject
    private Event<ScheduleStatus> scheduleStatusEvent;

    @PostConstruct
    public void init() {
        this.classrooms = entities.getAllClassroomsWithCorpsesAndDesires();
        this.coupleNumbers = CoupleNumber.values();
        this.dayOfWeeks = DayOfWeek.values();
        this.streams = entities.initializeStreams(entities.getAllStreamsWithTeacherAndSubject());
        this.faculties = entities.getAllFaculties();
        this.groups = entities.getAllGroups();
        this.studyYears = StudyYear.values();
        this.scheduleStatus = new ScheduleStatus(streams.size() - 1);
    }

    public List<Couple> initialApproximateSchedule() {
        List<Couple> couples = initialApproximationBehaviour.initialApproximateSchedule(classrooms,
                coupleNumbers, dayOfWeeks, streams, restrictionBehaviour, scheduleStatus, scheduleStatusEvent);
        entities.createEntity(couples);
        return couples;
    }

    public List<Couple> improveSchedule(List<Couple> couples) {
        List<Couple> improvedCouples = improvingBehaviour.improveSchedule(couples);
        entities.updateEntity(improvedCouples);
        return improvedCouples;
    }

    public void exportSchedule(List<Couple> couples) {
        exportBehaviour.exportSchedule(couples, faculties, studyYears, dayOfWeeks, coupleNumbers, groups);
    }

    public String getFilePath() {
        return exportBehaviour.getFilePath();
    }

    public void setImprovingBehaviour(ImprovingBehaviour improvingBehaviour) {
        this.improvingBehaviour = improvingBehaviour;
    }

    public void setRestrictionBehaviour(RestrictionBehaviour restrictionBehaviour) {
        this.restrictionBehaviour = restrictionBehaviour;
    }

    public void setInitialApproximationBehaviour(InitialApproximationBehaviour initialApproximationBehaviour) {
        this.initialApproximationBehaviour = initialApproximationBehaviour;
    }

    public void setExportBehaviour(ExportBehaviour exportBehaviour) {
        this.exportBehaviour = exportBehaviour;
    }

    public DatabaseEntities getEntities() {
        return entities;
    }
}
