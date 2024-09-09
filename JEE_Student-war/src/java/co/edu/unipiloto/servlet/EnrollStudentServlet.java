package co.edu.unipiloto.servlet;

import co.edu.unipiloto.arquitectura.student.entity.CourseStudents;
import co.edu.unipiloto.arquitectura.student.entity.Courses;
import co.edu.unipiloto.arquitectura.student.entity.Student;
import co.edu.unipiloto.arquitectura.student.session.CourseFacadeLocal;
import co.edu.unipiloto.arquitectura.student.session.StudentFacadeLocal;
import co.edu.unipiloto.arquitectura.student.session.CourseStudentsFacadeLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EnrollStudentServlet", urlPatterns = {"/EnrollStudentServlet"})
public class EnrollStudentServlet extends HttpServlet {

    @EJB
    private StudentFacadeLocal studentFacade;

    @EJB
    private CourseFacadeLocal courseFacade;

    @EJB
    private CourseStudentsFacadeLocal courseStudentsFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String studentIdStr = request.getParameter("studentId");
        String courseCode = request.getParameter("courseCode");

        if (studentIdStr != null && !studentIdStr.trim().isEmpty() && courseCode != null && !courseCode.trim().isEmpty()) {
            try {
                Integer studentId = Integer.valueOf(studentIdStr);
                Student student = studentFacade.find(studentId);
                Courses course = courseFacade.find(courseCode);

                if (student != null && course != null) {
                    // Aquí deberías verificar si la inscripción ya existe de alguna manera.
                    // Si no tienes un método específico para esto, considera implementar una validación manual.

                    CourseStudents courseStudents = new CourseStudents();
                    courseStudents.setStudentId(studentId);
                    courseStudents.setCourseCode(courseCode);

                    courseStudentsFacade.create(courseStudents);

                    request.setAttribute("message", "Student enrolled successfully.");
                } else {
                    request.setAttribute("message", "Student or Course not found.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid Student ID format.");
            } catch (Exception e) {
                request.setAttribute("message", "Error enrolling student: " + e.getMessage());
            }
        } else {
            request.setAttribute("message", "Student ID and Course Code cannot be empty.");
        }

        request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Enrolls students in courses.";
    }
}
