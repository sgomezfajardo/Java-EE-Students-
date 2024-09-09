package co.edu.unipiloto.servlet;

import co.edu.unipiloto.arquitectura.student.entity.Courses;
import co.edu.unipiloto.arquitectura.student.session.CourseFacadeLocal;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CoursesServlet", urlPatterns = {"/CoursesServlet"})
public class CoursesServlet extends HttpServlet {

    @EJB
    private CourseFacadeLocal courseFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if ("Add".equals(action)) {
            String courseCode = request.getParameter("courseCode");
            String courseName = request.getParameter("courseName");

            if (courseCode != null && courseName != null && !courseCode.isEmpty() && !courseName.isEmpty()) {
                Courses course = new Courses();
                course.setCoursecode(courseCode);
                course.setCoursename(courseName);
                courseFacade.create(course);
            }
        } else if ("See Table".equals(action)) {
            List<Courses> courses = courseFacade.findAll();
            request.setAttribute("allCourses", courses);
            request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
            return;
        }

        // Default to show the page without the course list
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
        return "CoursesServlet";
    }
}
