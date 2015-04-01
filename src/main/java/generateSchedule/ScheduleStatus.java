package generateSchedule;

/**
 * Created by Sergey on 05.03.2015.
 */
public class ScheduleStatus {
    public final static int ALL_PROGRESS_PERCENT = 100;
    private double countIterations;
    private int currentIteration;
    private boolean isGenerating;

    public ScheduleStatus(int countIterations) {
        this.countIterations = countIterations;
        this.isGenerating = false;
    }

    public int getCurrentStatus() {
        return (int)(currentIteration / countIterations * ALL_PROGRESS_PERCENT);
    }

    public void setCurrentIteration(int currentIteration) {
        this.currentIteration = currentIteration;
    }

    public boolean isGenerating() {
        return isGenerating;
    }

    public void setGenerating(boolean isGenerating) {
        this.isGenerating = isGenerating;
    }
}
