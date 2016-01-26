package bg.rashev.xml.distributor.model;

import bg.rashev.xml.distributor.enums.ExamType;
import bg.rashev.xml.distributor.enums.Subject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Exam")
@XmlType(propOrder = {"subject", "examType", "examStart", "examEnd"})
public class Exam {

    private Subject subject;
    private ExamType examType;
    private Time examStart;
    private Time examEnd;

    @SuppressWarnings("unused")
    public Exam() {

    }

    public Exam(Subject subject, ExamType examType, Time examStart, Time examEnd) {
        this.subject = subject;
        this.examType = examType;
        this.examStart = new Time(examStart);
        this.examEnd = new Time(examEnd);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "subject=" + subject +
                ", examType=" + examType +
                ", examStart=" + examStart +
                ", examEnd=" + examEnd +
                '}';
    }

    @XmlAttribute
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @XmlElement
    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    @XmlElement
    public Time getExamStart() {
        return examStart;
    }

    public void setExamStart(Time examStart) {
        this.examStart = examStart;
    }

    @XmlElement
    public Time getExamEnd() {
        return examEnd;
    }

    public void setExamEnd(Time examEnd) {
        this.examEnd = examEnd;
    }
}
