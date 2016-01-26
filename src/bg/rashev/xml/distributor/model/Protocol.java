package bg.rashev.xml.distributor.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by CyberSirius on 15-Jan-16.
 */
@XmlRootElement(name = "Protocol")
@XmlType(propOrder = {"exam", "hall", "students"})
public class Protocol {
    private List<Student> students = new ArrayList<>();//probably should be a map against the place in hall
    private Exam exam;
    private Hall hall;

    public Protocol() {
    }

    public Protocol(List<Student> students, Exam exam, Hall hallNumber) {
        this.students = students;
        Collections.sort(this.students, (s1, s2) -> Integer.compare(s1.getFacultyNumber(), s2.getFacultyNumber()));
        this.exam = exam;
        this.hall = hallNumber;//if seats too little, exam cannot happen here
    }

    @XmlElement
    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @XmlElement
    private Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @XmlElement
    private Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public StudentPass getStudentPass(int facultyNumber) {
        int placeInHall = 1;
        for (Student student : students) {
            if (student.hasFacultyNumber(facultyNumber))
                return new StudentPass(student, placeInHall, getExam(), getHall());
            placeInHall++;
        }
        throw new IllegalArgumentException("This student is not allowed in this exam!");
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "students=" + students +
                ", exam=" + exam +
                ", hall=" + hall +
                '}';
    }
}
