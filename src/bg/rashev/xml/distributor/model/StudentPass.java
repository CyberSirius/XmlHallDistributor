package bg.rashev.xml.distributor.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "StudentPass")
@XmlType(propOrder = {"student", "exam", "hall", "placeInHall"})
public class StudentPass {
    private Student student;
    private Exam exam;
    private Hall hall;
    private int placeInHall;

    @SuppressWarnings("unused")
    public StudentPass() {
    }

    public StudentPass(Student student, int placeInHall, Exam exam, Hall hall) {
        this.student = new Student(student);
        this.placeInHall = placeInHall;
        this.exam = exam;
        this.hall = hall;
    }

    @Override
    public String toString() {
        return "StudentPass{" +
                "student=" + student +
                ", placeInHall=" + placeInHall +
                ", exam=" + exam +
                ", hall=" + hall +
                '}';
    }

    @XmlAttribute
    public int getFacultyNumber() {
        return student.getFacultyNumber();
    }

    @XmlElement
    public Student getStudent() {

        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @XmlElement
    public int getPlaceInHall() {
        return placeInHall;
    }

    public void setPlaceInHall(int placeInHall) {
        this.placeInHall = placeInHall;
    }

    @XmlElement
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @XmlElement
    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

}
