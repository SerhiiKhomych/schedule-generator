package entities;

/**
 * Created by Sergey on 28.12.2014.
 */
public enum StudyYear {
    FIRST("1 курс"),
    SECOND("2 курс"),
    THIRD("3 курс"),
    FOURTH("4 курс"),
    FIFTH("5 курс");

    private String label;

    StudyYear(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
