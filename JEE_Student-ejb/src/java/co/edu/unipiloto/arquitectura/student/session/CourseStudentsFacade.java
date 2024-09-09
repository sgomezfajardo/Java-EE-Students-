package co.edu.unipiloto.arquitectura.student.session;

import co.edu.unipiloto.arquitectura.student.entity.CourseStudents;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CourseStudentsFacade implements CourseStudentsFacadeLocal {

    @PersistenceContext(unitName = "student-PU")
    private EntityManager em;

    @Override
    public void create(CourseStudents courseStudents) {
        em.persist(courseStudents);
    }

    @Override
    public void edit(CourseStudents courseStudents) {
        em.merge(courseStudents);
    }

    @Override
    public void remove(CourseStudents courseStudents) {
        em.remove(em.merge(courseStudents));
    }

    @Override
    public CourseStudents find(Object id) {
        return em.find(CourseStudents.class, id);
    }

    @Override
    public List<CourseStudents> findAll() {
        return em.createQuery("SELECT c FROM CourseStudents c", CourseStudents.class).getResultList();
    }

    @Override
    public CourseStudents findByStudentAndCourse(Integer studentId, String courseCode) {
        TypedQuery<CourseStudents> query = em.createQuery(
            "SELECT c FROM CourseStudents c WHERE c.student.studentid = :studentId AND c.course.coursecode = :courseCode",
            CourseStudents.class);
        query.setParameter("studentId", studentId);
        query.setParameter("courseCode", courseCode);
        List<CourseStudents> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
