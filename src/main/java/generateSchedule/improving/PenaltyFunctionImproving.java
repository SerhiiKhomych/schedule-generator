package generateSchedule.improving;

import entities.Couple;
import generateSchedule.improving.ImprovingBehaviour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 07.02.2015.
 */
public class PenaltyFunctionImproving implements ImprovingBehaviour {
    @Override
    public List<Couple> improveSchedule(List<Couple> couples) {
        return couples;
    }
}