package bg.rashev.xml.distributor.model;

import bg.rashev.xml.distributor.enums.Faculty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Hall")
@XmlType(propOrder = {"hallNumber", "faculty", "numberOfSeats"})
public class Hall {
    private Faculty faculty;
    private int hallNumber;//maybe it should have letters
    private int numberOfSeats;

    public Hall() {
    }

    public Hall(Faculty faculty, int hallNumber, int numberOfSeats) {
        this.faculty = faculty;
        this.hallNumber = hallNumber;
        this.numberOfSeats = numberOfSeats;
    }

    @XmlElement
    public Faculty getFaculty() {

        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @XmlAttribute
    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    @XmlElement
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "faculty=" + faculty +
                ", hallNumber=" + hallNumber +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
