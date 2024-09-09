package co.edu.unipiloto.arquitectura.student.session;

import co.edu.unipiloto.arquitectura.student.entity.CourseStudents;
import javax.ejb.Local;
import java.util.List;

@Local
public interface CourseStudentsFacadeLocal {
    void create(CourseStudents courseStudents);
    void edit(CourseStudents courseStudents);
    void remove(CourseStudents courseStudents);
    CourseStudents find(Object id);
    List<CourseStudents> findAll();
    CourseStudents findByStudentAndCourse(Integer studentId, String courseCode);
}
