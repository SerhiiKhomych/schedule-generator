package entities;

/**
 * Created by Sergey on 27.12.2014.
 */
public enum DayOfWeek {
    MONDAY_FIRST(0, "ПОНЕДІЛОК", "1 тиждень"),
    MONDAY_SECOND(1, "ПОНЕДІЛОК", "2 тиждень"),
    TUESDAY_FIRST(2, "ВІВТОРОК", "1 тиждень"),
    TUESDAY_SECOND(3, "ВІВТОРОК", "2 тиждень"),
    WEDNESDAY_FIRST(4, "СЕРЕДА", "1 тиждень"),
    WEDNESDAY_SECOND(5, "СЕРЕДА", "2 тиждень"),
    THURSDAY_FIRST(6, "ЧЕТВЕР", "1 тиждень"),
    THURSDAY_SECOND(7, "ЧЕТВЕР", "2 тиждень"),
    FRIDAY_FIRST(8, "П\'ЯТНИЦЯ", "1 тиждень"),
    FRIDAY_SECOND(9, "П\'ЯТНИЦЯ", "2 тиждень"),
    SATURDAY_FIRST(10, "CУБОТА", "1 тиждень"),
    SATURDAY_SECOND(11, "CУБОТА", "2 тиждень");

    private int value;
    private String label;
    private String weekType;

    DayOfWeek(int value, String label, String weekType) {
        this.value = value;
        this.label = label;
        this.weekType = weekType;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    public String getWeekType() {
        return weekType;
    }

    public boolean isFirstWeekDay() {
        if ((value % 2) == 0) {
            return true;
        } else {
            return false;
        }
    }
}