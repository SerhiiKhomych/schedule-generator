package entities;

/**
 * Created by Sergey on 28.12.2014.
 */
public enum CoupleNumber {
    FIRST(1, "8-30", "10-10"),
    SECOND(2, "10-25", "12-05"),
    THIRD(3, "12-20", "14-00"),
    FOURTH(4, "14-15", "15-15"),
    FIFTH(5, "15-30", "16-30");

    private String startDate;
    private int coupleNumber;
    private String endDate;

    CoupleNumber(int coupleNumber, String startDate, String endDate) {
        this.coupleNumber = coupleNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCoupleNumber() {
        return coupleNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
