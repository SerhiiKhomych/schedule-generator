package entities;

/**
 * Created by Sergey on 03.03.2015.
 */
public enum Desire {
    MONDAY_FIRST_1(DayOfWeek.MONDAY_FIRST, CoupleNumber.FIRST),
    MONDAY_FIRST_2(DayOfWeek.MONDAY_FIRST, CoupleNumber.SECOND),
    MONDAY_FIRST_3(DayOfWeek.MONDAY_FIRST, CoupleNumber.THIRD),
    MONDAY_FIRST_4(DayOfWeek.MONDAY_FIRST, CoupleNumber.FOURTH),
    MONDAY_FIRST_5(DayOfWeek.MONDAY_FIRST, CoupleNumber.FIFTH),
    MONDAY_SECOND_1(DayOfWeek.MONDAY_SECOND, CoupleNumber.FIRST),
    MONDAY_SECOND_2(DayOfWeek.MONDAY_SECOND, CoupleNumber.SECOND),
    MONDAY_SECOND_3(DayOfWeek.MONDAY_SECOND, CoupleNumber.THIRD),
    MONDAY_SECOND_4(DayOfWeek.MONDAY_SECOND, CoupleNumber.FOURTH),
    MONDAY_SECOND_5(DayOfWeek.MONDAY_SECOND, CoupleNumber.FIFTH),
    TUESDAY_FIRST_1(DayOfWeek.TUESDAY_FIRST, CoupleNumber.FIRST),
    TUESDAY_FIRST_2(DayOfWeek.TUESDAY_FIRST, CoupleNumber.SECOND),
    TUESDAY_FIRST_3(DayOfWeek.TUESDAY_FIRST, CoupleNumber.THIRD),
    TUESDAY_FIRST_4(DayOfWeek.TUESDAY_FIRST, CoupleNumber.FOURTH),
    TUESDAY_FIRST_5(DayOfWeek.TUESDAY_FIRST, CoupleNumber.FIFTH),
    TUESDAY_SECOND_1(DayOfWeek.TUESDAY_SECOND, CoupleNumber.FIRST),
    TUESDAY_SECOND_2(DayOfWeek.TUESDAY_SECOND, CoupleNumber.SECOND),
    TUESDAY_SECOND_3(DayOfWeek.TUESDAY_SECOND, CoupleNumber.THIRD),
    TUESDAY_SECOND_4(DayOfWeek.TUESDAY_SECOND, CoupleNumber.FOURTH),
    TUESDAY_SECOND_5(DayOfWeek.TUESDAY_SECOND, CoupleNumber.FIFTH),
    WEDNESDAY_FIRST_1(DayOfWeek.WEDNESDAY_FIRST, CoupleNumber.FIRST),
    WEDNESDAY_FIRST_2(DayOfWeek.WEDNESDAY_FIRST, CoupleNumber.SECOND),
    WEDNESDAY_FIRST_3(DayOfWeek.WEDNESDAY_FIRST, CoupleNumber.THIRD),
    WEDNESDAY_FIRST_4(DayOfWeek.WEDNESDAY_FIRST, CoupleNumber.FOURTH),
    WEDNESDAY_FIRST_5(DayOfWeek.WEDNESDAY_FIRST, CoupleNumber.FIFTH),
    WEDNESDAY_SECOND_1(DayOfWeek.WEDNESDAY_SECOND, CoupleNumber.FIRST),
    WEDNESDAY_SECOND_2(DayOfWeek.WEDNESDAY_SECOND, CoupleNumber.SECOND),
    WEDNESDAY_SECOND_3(DayOfWeek.WEDNESDAY_SECOND, CoupleNumber.THIRD),
    WEDNESDAY_SECOND_4(DayOfWeek.WEDNESDAY_SECOND, CoupleNumber.FOURTH),
    WEDNESDAY_SECOND_5(DayOfWeek.WEDNESDAY_SECOND, CoupleNumber.FIFTH),
    THURSDAY_FIRST_1(DayOfWeek.THURSDAY_FIRST, CoupleNumber.FIRST),
    THURSDAY_FIRST_2(DayOfWeek.THURSDAY_FIRST, CoupleNumber.SECOND),
    THURSDAY_FIRST_3(DayOfWeek.THURSDAY_FIRST, CoupleNumber.THIRD),
    THURSDAY_FIRST_4(DayOfWeek.THURSDAY_FIRST, CoupleNumber.FOURTH),
    THURSDAY_FIRST_5(DayOfWeek.THURSDAY_FIRST, CoupleNumber.FIFTH),
    THURSDAY_SECOND_1(DayOfWeek.THURSDAY_SECOND, CoupleNumber.FIRST),
    THURSDAY_SECOND_2(DayOfWeek.THURSDAY_SECOND, CoupleNumber.SECOND),
    THURSDAY_SECOND_3(DayOfWeek.THURSDAY_SECOND, CoupleNumber.THIRD),
    THURSDAY_SECOND_4(DayOfWeek.THURSDAY_SECOND, CoupleNumber.FOURTH),
    THURSDAY_SECOND_5(DayOfWeek.THURSDAY_SECOND, CoupleNumber.FIFTH),
    FRIDAY_FIRST_1(DayOfWeek.FRIDAY_FIRST, CoupleNumber.FIRST),
    FRIDAY_FIRST_2(DayOfWeek.FRIDAY_FIRST, CoupleNumber.SECOND),
    FRIDAY_FIRST_3(DayOfWeek.FRIDAY_FIRST, CoupleNumber.THIRD),
    FRIDAY_FIRST_4(DayOfWeek.FRIDAY_FIRST, CoupleNumber.FOURTH),
    FRIDAY_FIRST_5(DayOfWeek.FRIDAY_FIRST, CoupleNumber.FIFTH),
    FRIDAY_SECOND_1(DayOfWeek.FRIDAY_SECOND, CoupleNumber.FIRST),
    FRIDAY_SECOND_2(DayOfWeek.FRIDAY_SECOND, CoupleNumber.SECOND),
    FRIDAY_SECOND_3(DayOfWeek.FRIDAY_SECOND, CoupleNumber.THIRD),
    FRIDAY_SECOND_4(DayOfWeek.FRIDAY_SECOND, CoupleNumber.FOURTH),
    FRIDAY_SECOND_5(DayOfWeek.FRIDAY_SECOND, CoupleNumber.FIFTH),
    SATURDAY_FIRST_1(DayOfWeek.SATURDAY_FIRST, CoupleNumber.FIRST),
    SATURDAY_FIRST_2(DayOfWeek.SATURDAY_FIRST, CoupleNumber.SECOND),
    SATURDAY_FIRST_3(DayOfWeek.SATURDAY_FIRST, CoupleNumber.THIRD),
    SATURDAY_FIRST_4(DayOfWeek.SATURDAY_FIRST, CoupleNumber.FOURTH),
    SATURDAY_FIRST_5(DayOfWeek.SATURDAY_FIRST, CoupleNumber.FIFTH),
    SATURDAY_SECOND_1(DayOfWeek.SATURDAY_SECOND, CoupleNumber.FIRST),
    SATURDAY_SECOND_2(DayOfWeek.SATURDAY_SECOND, CoupleNumber.SECOND),
    SATURDAY_SECOND_3(DayOfWeek.SATURDAY_SECOND, CoupleNumber.THIRD),
    SATURDAY_SECOND_4(DayOfWeek.SATURDAY_SECOND, CoupleNumber.FOURTH),
    SATURDAY_SECOND_5(DayOfWeek.SATURDAY_SECOND, CoupleNumber.FIFTH);

    private DayOfWeek dayOfWeek;
    private CoupleNumber coupleNumber;

    Desire(DayOfWeek dayOfWeek, CoupleNumber coupleNumber) {
        this.dayOfWeek = dayOfWeek;
        this.coupleNumber = coupleNumber;
    }

    public static Desire getDesire(String desire) {
        switch (desire) {
            case "MONDAY_FIRST_1": return Desire.MONDAY_FIRST_1;
            case "MONDAY_FIRST_2": return Desire.MONDAY_FIRST_2;
            case "MONDAY_FIRST_3": return Desire.MONDAY_FIRST_3;
            case "MONDAY_FIRST_4": return Desire.MONDAY_FIRST_4;
            case "MONDAY_FIRST_5": return Desire.MONDAY_FIRST_5;
            case "MONDAY_SECOND_1": return Desire.MONDAY_SECOND_1;
            case "MONDAY_SECOND_2": return Desire.MONDAY_SECOND_2;
            case "MONDAY_SECOND_3": return Desire.MONDAY_SECOND_3;
            case "MONDAY_SECOND_4": return Desire.MONDAY_SECOND_4;
            case "MONDAY_SECOND_5": return Desire.MONDAY_SECOND_5;
            case "TUESDAY_FIRST_1": return Desire.TUESDAY_FIRST_1;
            case "TUESDAY_FIRST_2": return Desire.TUESDAY_FIRST_2;
            case "TUESDAY_FIRST_3": return Desire.TUESDAY_FIRST_3;
            case "TUESDAY_FIRST_4": return Desire.TUESDAY_FIRST_4;
            case "TUESDAY_FIRST_5": return Desire.TUESDAY_FIRST_5;
            case "TUESDAY_SECOND_1": return Desire.TUESDAY_SECOND_1;
            case "TUESDAY_SECOND_2": return Desire.TUESDAY_SECOND_2;
            case "TUESDAY_SECOND_3": return Desire.TUESDAY_SECOND_3;
            case "TUESDAY_SECOND_4": return Desire.TUESDAY_SECOND_4;
            case "TUESDAY_SECOND_5": return Desire.TUESDAY_SECOND_5;
            case "WEDNESDAY_FIRST_1": return Desire.WEDNESDAY_FIRST_1;
            case "WEDNESDAY_FIRST_2": return Desire.WEDNESDAY_FIRST_2;
            case "WEDNESDAY_FIRST_3": return Desire.WEDNESDAY_FIRST_3;
            case "WEDNESDAY_FIRST_4": return Desire.WEDNESDAY_FIRST_4;
            case "WEDNESDAY_FIRST_5": return Desire.WEDNESDAY_FIRST_5;
            case "WEDNESDAY_SECOND_1": return Desire.WEDNESDAY_SECOND_1;
            case "WEDNESDAY_SECOND_2": return Desire.WEDNESDAY_SECOND_2;
            case "WEDNESDAY_SECOND_3": return Desire.WEDNESDAY_SECOND_3;
            case "WEDNESDAY_SECOND_4": return Desire.WEDNESDAY_SECOND_4;
            case "WEDNESDAY_SECOND_5": return Desire.WEDNESDAY_SECOND_5;
            case "THURSDAY_FIRST_1": return Desire.THURSDAY_FIRST_1;
            case "THURSDAY_FIRST_2": return Desire.THURSDAY_FIRST_2;
            case "THURSDAY_FIRST_3": return Desire.THURSDAY_FIRST_3;
            case "THURSDAY_FIRST_4": return Desire.THURSDAY_FIRST_4;
            case "THURSDAY_FIRST_5": return Desire.THURSDAY_FIRST_5;
            case "THURSDAY_SECOND_1": return Desire.THURSDAY_SECOND_1;
            case "THURSDAY_SECOND_2": return Desire.THURSDAY_SECOND_2;
            case "THURSDAY_SECOND_3": return Desire.THURSDAY_SECOND_3;
            case "THURSDAY_SECOND_4": return Desire.THURSDAY_SECOND_4;
            case "THURSDAY_SECOND_5": return Desire.THURSDAY_SECOND_5;
            case "FRIDAY_FIRST_1": return Desire.FRIDAY_FIRST_1;
            case "FRIDAY_FIRST_2": return Desire.FRIDAY_FIRST_2;
            case "FRIDAY_FIRST_3": return Desire.FRIDAY_FIRST_3;
            case "FRIDAY_FIRST_4": return Desire.FRIDAY_FIRST_4;
            case "FRIDAY_FIRST_5": return Desire.FRIDAY_FIRST_5;
            case "FRIDAY_SECOND_1": return Desire.FRIDAY_SECOND_1;
            case "FRIDAY_SECOND_2": return Desire.FRIDAY_SECOND_2;
            case "FRIDAY_SECOND_3": return Desire.FRIDAY_SECOND_3;
            case "FRIDAY_SECOND_4": return Desire.FRIDAY_SECOND_4;
            case "FRIDAY_SECOND_5": return Desire.FRIDAY_SECOND_5;
            case "SATURDAY_FIRST_1": return Desire.SATURDAY_FIRST_1;
            case "SATURDAY_FIRST_2": return Desire.SATURDAY_FIRST_2;
            case "SATURDAY_FIRST_3": return Desire.SATURDAY_FIRST_3;
            case "SATURDAY_FIRST_4": return Desire.SATURDAY_FIRST_4;
            case "SATURDAY_FIRST_5": return Desire.SATURDAY_FIRST_5;
            case "SATURDAY_SECOND_1": return Desire.SATURDAY_SECOND_1;
            case "SATURDAY_SECOND_2": return Desire.SATURDAY_SECOND_2;
            case "SATURDAY_SECOND_3": return Desire.SATURDAY_SECOND_3;
            case "SATURDAY_SECOND_4": return Desire.FRIDAY_SECOND_4;
            case "SATURDAY_SECOND_5": return Desire.SATURDAY_SECOND_5;
            default: return null;
        }
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public CoupleNumber getCoupleNumber() {
        return coupleNumber;
    }
}
