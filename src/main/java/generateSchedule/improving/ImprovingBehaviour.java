package generateSchedule.improving;

import entities.Couple;

import java.util.List;

/**
 * Created by Sergey on 07.02.2015.
 */
public interface ImprovingBehaviour {
    public List<Couple> improveSchedule(List<Couple> couples);
}
