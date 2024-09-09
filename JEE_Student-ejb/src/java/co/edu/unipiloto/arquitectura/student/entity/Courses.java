package co.edu.unipiloto.arquitectura.student.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Courses")
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(max = 10)
    @Column(name = "coursecode")
    private String coursecode;

    @NotNull
    @Size(max = 100)
    @Column(name = "coursename")
    private String coursename;

    // Constructor vacío requerido por JPA
    public Courses() {
    }

    public Courses(String coursecode, String coursename) {
        this.coursecode = coursecode;
        this.coursename = coursename;
    }

    // Getters y Setters
    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    // hashCode, equals y toString para la entidad
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coursecode != null ? coursecode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        return !((this.coursecode == null && other.coursecode != null) || (this.coursecode != null && !this.coursecode.equals(other.coursecode)));
    }

    @Override
    public String toString() {
        return "co.edu.unipiloto.arquitectura.student.entity.Course[ coursecode=" + coursecode + " ]";
    }
}
