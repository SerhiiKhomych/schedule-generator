package interfaceViews.controllers;

import generateSchedule.GeneratingScheduleWIthLocalTransformations;
import generateSchedule.ScheduleStatus;
import generateSchedule.export.ExcelExport;

import java.io.*;
import java.net.MalformedURLException;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

@Named("scheduleController")
@SessionScoped
public class ScheduleController implements Serializable {
    @EJB
    private GeneratingScheduleWIthLocalTransformations schedule;

    public ScheduleController() {};

    private int progress;

    public void generateSchedule() {
        schedule.exportSchedule(schedule.initialApproximateSchedule());
    }

    public void setProgress(@Observes(notifyObserver = Reception.IF_EXISTS) ScheduleStatus scheduleStatus) {
        setProgress(scheduleStatus.getCurrentStatus());
    }

    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Розклад згенеровано", "Розклад згенеровано"));
    }

    public GeneratingScheduleWIthLocalTransformations getSchedule() {
        return schedule;
    }

    public void setSchedule(GeneratingScheduleWIthLocalTransformations schedule) {
        this.schedule = schedule;
    }

    public String getFilePath() {
        return schedule.getFilePath();
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}