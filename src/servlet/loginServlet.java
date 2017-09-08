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

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    private String tempUsername, tempPassword;
    private Model model = Model.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        tempUsername = request.getParameter("username");
        tempPassword = request.getParameter("password");

        PrintWriter writer = response.getWriter();
        String htmlRespone = "<html>";

        for (int i = 0; i < model.persons.size(); i++) {
            if (model.getPersons().get(i).getUsername().equals(tempUsername) && model.getPersons().get(i).getPassword().equals(tempPassword)) {
                System.out.println("Succesvolle inlog TEST");
                htmlRespone += "Welcome " + tempUsername + ", u succesfully logged in!" + "<br/>";
            } else {
                System.out.println("Niet succesvolle inlog TEST");
                htmlRespone += "The username/password combination is incorrect, please try again!" + "<br/>";
                // Hier moet hij redirecting naar een foutieve inlog pagina.html --> staat in opdrachtbeschrijving.
            }
        }

        htmlRespone += "</html>";
        writer.println(htmlRespone);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
