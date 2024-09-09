package co.edu.unipiloto.arquitectura.student.session;

import co.edu.unipiloto.arquitectura.student.entity.Courses;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CourseFacadeLocal {

    void create(Courses course);

    void edit(Courses course);

    void remove(Courses course);

    Courses find(Object id);

    List<Courses> findAll();

    List<Courses> findRange(int[] range);

    int count();
}
