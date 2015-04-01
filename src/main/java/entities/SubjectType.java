package entities;

/**
 * Created by Sergey on 27.12.2014.
 */
public enum SubjectType {
    LECTURE("Лекція"),
    PRACTICE("Практика"),
    LAB_WORK("Лаб. робота");

    private String label;

    SubjectType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
