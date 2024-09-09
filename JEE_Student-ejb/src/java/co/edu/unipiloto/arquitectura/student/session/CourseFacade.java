package co.edu.unipiloto.arquitectura.student.session;

import co.edu.unipiloto.arquitectura.student.entity.Courses;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CourseFacade implements CourseFacadeLocal {

    @PersistenceContext(unitName = "student-PU")
    private EntityManager em;

    @Override
    public void create(Courses course) {
        em.persist(course);
    }

    @Override
    public void edit(Courses course) {
        em.merge(course);
    }

    @Override
    public void remove(Courses course) {
        em.remove(em.merge(course));
    }

    @Override
    public Courses find(Object id) {
        return em.find(Courses.class, id);
    }

    @Override
    public List<Courses> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Courses.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Courses> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Courses.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Courses> rt = cq.from(Courses.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}

