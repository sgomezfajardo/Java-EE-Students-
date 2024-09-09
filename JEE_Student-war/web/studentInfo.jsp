<%@ page import="java.util.List" %>
<%@ page import="co.edu.unipiloto.arquitectura.student.entity.Student" %>
<%@ page import="co.edu.unipiloto.arquitectura.student.entity.Courses" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student and Course Information</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
    <!-- Sección para información de estudiantes -->
    <h1>Student Information</h1>

    <!-- Formulario para agregar, editar, eliminar, buscar y ver estudiantes -->
    <form action="./StudentServlet" method="POST">
        <fieldset>
            <legend>Student Information</legend>
            <label for="studentId">Student Id:</label>
            <input type="text" id="studentId" name="studentId" value="" /><br/><br/>

            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="" /><br/><br/>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="" /><br/><br/>

            <label for="yearLevel">Year Level:</label>
            <input type="text" id="yearLevel" name="yearLevel" value="" /><br/><br/>

            <input type="submit" name="action" value="Add" />
            <input type="submit" name="action" value="Edit" />
            <input type="submit" name="action" value="Delete" />
            <input type="submit" name="action" value="Search" />
            <input type="submit" name="action" value="See Table" />
        </fieldset>
    </form>

    <!-- Tabla para mostrar la lista de estudiantes -->
    <h2>Student List</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Year Level</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${allStudents}">
                <tr>
                    <td>${student.studentid}</td>
                    <td>${student.firstname}</td>
                    <td>${student.lastname}</td>
                    <td>${student.yearlevel}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Sección para gestión de cursos -->
    <h1>Course Information</h1>

    <!-- Formulario para agregar, editar, eliminar y ver cursos -->
    <form action="./CoursesServlet" method="POST">
        <fieldset>
            <legend>Course Management</legend>
            <label for="courseCode">Course Code:</label>
            <input type="text" id="courseCode" name="courseCode" value="" /><br/><br/>

            <label for="courseName">Course Name:</label>
            <input type="text" id="courseName" name="courseName" value="" /><br/><br/>

            <input type="submit" name="action" value="Add" />
            <input type="submit" name="action" value="Edit" />
            <input type="submit" name="action" value="Delete" />
            <input type="submit" name="action" value="See Table" />
        </fieldset>
    </form>

    <!-- Tabla para mostrar la lista de cursos -->
    <h2>Course List</h2>
    <table>
        <thead>
            <tr>
                <th>Course Code</th>
                <th>Course Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${allCourses}">
                <tr>
                    <td>${course.coursecode}</td>
                    <td>${course.coursename}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Sección para inscribir estudiantes en cursos -->
    <h1>Enroll Student in a Course</h1>

    <form action="./EnrollStudentServlet" method="POST">
        <fieldset>
            <legend>Enroll Student</legend>
            <label for="enrollStudentId">Student Id:</label>
            <input type="text" id="enrollStudentId" name="studentId" value="" /><br/><br/>

            <label for="courseCodeEnroll">Course Code:</label>
            <input type="text" id="courseCodeEnroll" name="courseCode" value="" /><br/><br/>

            <input type="submit" name="action" value="Enroll Student" />
        </fieldset>
    </form>
</body>
</html>
