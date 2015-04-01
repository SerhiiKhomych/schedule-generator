package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sergey on 28.12.2014.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllCouples", query = "SELECT couple FROM Couple couple")
})
public class Couple implements DBEntity{
    @Id
    @GeneratedValue
    @Column(name = "couple_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
    @Enumerated(EnumType.STRING)
    @Column(name = "couple_number")
    private CoupleNumber coupleNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "week_day")
    private DayOfWeek dayOfWeek;
    @OneToOne
    @JoinColumn(name = "stream_id")
    private Stream stream;

    public Couple() {
    }

    public Couple(Classroom classroom, CoupleNumber coupleNumber, DayOfWeek dayOfWeek, Stream stream) {
        this.classroom = classroom;
        this.coupleNumber = coupleNumber;
        this.dayOfWeek = dayOfWeek;
        this.stream = stream;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public CoupleNumber getCoupleNumber() {
        return coupleNumber;
    }

    public void setCoupleNumber(CoupleNumber coupleNumber) {
        this.coupleNumber = coupleNumber;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public static String getCoupleForGroupByTime(List<Couple> couples, CoupleNumber coupleNumber, DayOfWeek dayOfWeek, Group group) {
        for(Couple couple: couples) {
            if ((couple.getCoupleNumber().equals(coupleNumber)) &&
                    (couple.getDayOfWeek().equals(dayOfWeek)) &&
                    isGroupOnCouple(couple, group)) {
                return couple.toString();
            }
        }
        return "";
    }

    private static boolean isGroupOnCouple(Couple couple, Group group) {
        for (Group groupOnCouple: couple.getStream().getGroups()) {
            if (group.equals(groupOnCouple)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return stream.getSubject().getName() + "(" +
                stream.getSubject().getSubjectType().getLabel() + ")" + "\n" +
                classroom.getName() + "-" +
                classroom.getCorps().getName() + "\n" +
                stream.getTeacher().getFullName();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Couple couple = (Couple) obj;
        return dayOfWeek.equals(couple.getDayOfWeek()) &&
                coupleNumber.equals(couple.getCoupleNumber());
    }
}
