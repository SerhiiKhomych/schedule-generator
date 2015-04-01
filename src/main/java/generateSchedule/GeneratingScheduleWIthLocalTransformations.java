package generateSchedule;

import generateSchedule.export.ExcelExport;
import generateSchedule.improving.PenaltyFunctionImproving;
import generateSchedule.initializing.FirstCorrect;
import generateSchedule.restrictions.BasicRestrictions;

import javax.ejb.DependsOn;
import javax.ejb.Stateless;

/**
 * Created by Sergey on 07.02.2015.
 */
@Stateless
@DependsOn("DatabaseEntities")
public class GeneratingScheduleWIthLocalTransformations extends ScheduleGenerator{

    public GeneratingScheduleWIthLocalTransformations() {
        super.setImprovingBehaviour(new PenaltyFunctionImproving());
        super.setInitialApproximationBehaviour(new FirstCorrect());
        super.setRestrictionBehaviour(new BasicRestrictions());
        super.setExportBehaviour(new ExcelExport());
    }
}