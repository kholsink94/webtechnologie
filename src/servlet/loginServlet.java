package servlet;

import model.Model;
import model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    private String tempUsername, tempPassword;
    private Model model = Model.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        tempUsername = request.getParameter("username");
        tempPassword = request.getParameter("password");

        PrintWriter writer = response.getWriter();
        String htmlRespone = "<html>";

        if(ValidateLogin(tempUsername, tempPassword)){
            response.sendRedirect("home.html");
        } else{
            response.sendRedirect("failedLogin.html");
        }

        htmlRespone += "</html>";
        writer.println(htmlRespone);
    }

    private boolean ValidateLogin(String username, String password){
        ArrayList<Person> users = model.getPersons();

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)){
                return true;
            }
        }

        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
