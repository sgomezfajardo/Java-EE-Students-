package co.edu.unipiloto.arquitectura.student.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_students")
public class CourseStudents implements Serializable {

    @Id
    private Integer studentId;

    @Id
    private String courseCode;

    // Default constructor
    public CourseStudents() {
    }

    // Parameterized constructor
    public CourseStudents(Integer studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    // Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        hash += (courseCode != null ? courseCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CourseStudents)) {
            return false;
        }
        CourseStudents other = (CourseStudents) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        if ((this.courseCode == null && other.courseCode != null) || (this.courseCode != null && !this.courseCode.equals(other.courseCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.arquitectura.student.entity.CourseStudents[ studentId=" + studentId + ", courseCode=" + courseCode + " ]";
    }
}
