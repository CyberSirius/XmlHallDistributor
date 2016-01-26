package bg.rashev.xml.distributor.model;

import bg.rashev.xml.distributor.enums.Degree;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.regex.Pattern;

@XmlRootElement(name = "Student")
@XmlType(propOrder = {"firstName", "middleName", "lastName", "personalID", "facultyNumber", "degree", "course", "group"})
public class Student {
    private String firstName;
    private String middleName;
    private String lastName;
    private String personalID;
    private int facultyNumber;
    private int course;
    private int group;
    private Degree degree;

    public Student() {
    }

    public Student(Student secondStudent) {
        this.setFirstName(secondStudent.getFirstName());
        this.setMiddleName(secondStudent.getMiddleName());
        this.setLastName(secondStudent.getLastName());
        this.setPersonalID(secondStudent.getPersonalID());
        this.setFacultyNumber(secondStudent.getFacultyNumber());
        this.setCourse(secondStudent.getCourse());
        this.setGroup(secondStudent.getGroup());
        this.setDegree(secondStudent.getDegree());
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalID='" + personalID + '\'' +
                ", facultyNumber=" + facultyNumber +
                ", course=" + course +
                ", group=" + group +
                ", degree=" + degree +
                '}';
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (Pattern.matches("[A-Z][a-z]*", firstName)) {
            this.firstName = firstName;
        } else throw new IllegalArgumentException("Invalid name entered");
    }

    @XmlElement
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (!Pattern.matches("[A-Z][a-z]*", middleName))
            throw new IllegalArgumentException("Invalid name entered");
        this.middleName = middleName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (Pattern.matches("[A-Z][a-z]*", lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Invalid name entered");
        }
    }

    @XmlElement
    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        if (Pattern.matches("\\d{10}", personalID))
            this.personalID = personalID;
        else throw new IllegalArgumentException("Personal ID not in correct format!");
    }

    @XmlAttribute
    public int getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(int facultyNumber) {
        if (Pattern.matches("\\d{5,6}", Integer.toString(facultyNumber))) {
            this.facultyNumber = facultyNumber;
        } else {
            throw new IllegalArgumentException("Invalid faculty number is entered!");
        }
    }

    @XmlElement
    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if (Pattern.matches("[1-4]", Integer.toString(course)))
            this.course = course;
        else throw new IllegalArgumentException("Invalid course is entered");
    }

    @XmlElement
    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        if (Pattern.matches("[1-5]", Integer.toString(group)))
            this.group = group;
        else throw new IllegalArgumentException("Invalid group number entered!");
    }

    @XmlElement
    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public boolean hasFacultyNumber(int facultyNumber) {
        return facultyNumber == this.getFacultyNumber();
    }
}
