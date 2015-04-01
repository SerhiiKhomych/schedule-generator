package entities;

import org.primefaces.model.DualListModel;

import java.util.List;

/**
 * Created by sekh0713 on 22.01.2015.
 */
public interface Desirable {
    public Long getId();

    public List<Desire> getDesires();
    public void setDesires(List<Desire> desires);

    public DualListModel<Desire> getDualListDesires();
    public void setDualListDesires(DualListModel<Desire> dualGroupDesires);

}
