package servlet;

import model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {

    private Model model = Model.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        System.out.println(username);
        System.out.println(password);
        System.out.println(role);

        PrintWriter writer = response.getWriter();
        String htmlRespone = "<html>";

        if(!model.doesPersonExist(username)){
            model.addPerson(username, password, role);
            System.out.println("Person: " + username +  " has been added!, His password is : " + password + " and his role is: " + role);
            response.sendRedirect("login.html");
        } else{
            htmlRespone += "This username did already exist." + "<br/>";
            htmlRespone += "<a href='register'>try again.</a>";
        }

        htmlRespone += "</html>";
        writer.println(htmlRespone);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
