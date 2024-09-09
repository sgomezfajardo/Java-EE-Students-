/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.servlet;

import co.edu.unipiloto.arquitectura.student.entity.Student;
import co.edu.unipiloto.arquitectura.student.session.StudentFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JUAN SEBASTIAN GOMEZ F.
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentFacadeLocal studentFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        if ("See Table".equals(action)) {
            List<Student> estudiantes = studentFacade.findAll();
            request.setAttribute("allStudents", estudiantes);
            request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
        } else {
            // Manejar otras acciones (Add, Edit, Delete, Search)
            String idStr = request.getParameter("studentId");
            Integer id = idStr != null ? Integer.valueOf(idStr) : null;
            String firstname = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String yearLevelStr = request.getParameter("yearLevel");
            Integer yearLevel = yearLevelStr != null ? Integer.valueOf(yearLevelStr) : null;

            Student estudiante = new Student(id, firstname, lastName, yearLevel);
            
            if ("Add".equals(action)) {
                studentFacade.create(estudiante);
            } else if ("Edit".equals(action)) {
                studentFacade.edit(estudiante);
            } else if ("Delete".equals(action)) {
                Student studentToDelete = studentFacade.find(id);
                if (studentToDelete != null) {
                    studentFacade.remove(studentToDelete);
                }
            } else if ("Search".equals(action)) {
                Student studentToSearch = studentFacade.find(id);
                request.setAttribute("stud", studentToSearch);
            }
            
            request.setAttribute("allStudents", studentFacade.findAll());
            request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
        }
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
        return "Short description";
    }
}
