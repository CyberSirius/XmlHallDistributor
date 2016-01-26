package bg.rashev.xml.distributor.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.regex.Pattern;

@XmlRootElement(name = "Time")
@XmlType(propOrder = {"year", "month", "day", "hour", "minute"})
public class Time {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Time(Time time) {
        this.setYear(time.getYear());
        this.setMonth(time.getMonth());
        this.setDay(time.getDay());
        this.setHour(time.getHour());
        this.setMinute(time.getMinute());
    }

    @SuppressWarnings("unused")
    public Time() {
    }

    @Override
    public String toString() {
        return "Time{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }

    @XmlAttribute
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (Pattern.matches("\\d{4}", Integer.toString(year)))
            this.year = year;
        else throw new IllegalArgumentException("Year is wrong!");
    }

    @XmlAttribute
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (1 <= month && month <= 12)
            this.month = month;
        else throw new IllegalArgumentException("Month is wrong!");
    }

    @XmlAttribute
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (1 <= day && day <= 31)
            this.day = day;
        else throw new IllegalArgumentException("Day is wrong!");
    }

    @XmlAttribute
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if (0 <= hour && hour <= 23)
            this.hour = hour;
        else throw new IllegalArgumentException("Hour is wrong!");
    }

    @XmlAttribute
    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if (0 <= minute && minute <= 59)
            this.minute = minute;
        else throw new IllegalArgumentException("Minute is wrong!");
    }
}
