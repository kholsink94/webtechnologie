package servlet;

import model.DataProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {

    /*
    Attribute to save our dataProvider.
     */
    private DataProvider dataProvider = DataProvider.getInstance();

    /*
    doPost method, we have no post actions in our register html.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /*
    doGet method, we save the username and password and the role (radiobutton).
    Checking if the username, password and role fields are valid.
    Adding the person to our dataProvider.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        PrintWriter writer = response.getWriter();

        if (username.length() == 0) {
            writer.println("Username is invalid!");
        } else if (password.length() == 0) {
            writer.println("Password is invalid!");
        } else if (role.isEmpty()) {
            writer.println("Role is invalid!");
        }

        if (!dataProvider.doesPersonExist(username)) {
            dataProvider.addPerson(username, password, role);
            System.out.println("Person: " + username + " has been added!, His password is : " + password + " and his role is: " + role);
            response.sendRedirect("login.html");
        } else {
            String htmlRespone = "<html>";
            htmlRespone += "Person already exists!";
            htmlRespone += "</html>";
            writer.println(htmlRespone);
        }
    }
}
