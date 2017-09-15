package servlet;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    private String tempUsername, tempPassword;
    private Model model;
    private ArrayList<Room> specificRooms;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();

        tempUsername = request.getParameter("username");
        tempPassword = request.getParameter("password");

        model = (Model) getServletContext().getAttribute(Model.class.getName());

        if (ValidateLogin(tempUsername, tempPassword)) {

            if (model.isHirer(tempUsername)) {
                Person currentUser = (Person) session.getAttribute("person");
                if (currentUser == null) {
                    currentUser = new Hirer(tempUsername, tempPassword);
                    session.setAttribute("person", currentUser);
                } else {
                    session.removeAttribute("person");
                    currentUser = new Hirer(tempUsername, tempPassword);
                    session.setAttribute("person", currentUser);
                }
                RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/hirer.html");
                dispatch.forward(request, response);
            } else if (!model.isHirer(tempUsername)) {
                Person currentUser = (Person) session.getAttribute("person");
                if (currentUser == null) {
                    currentUser = new Owner(tempUsername, tempPassword);
                    session.setAttribute("person", currentUser);
                } else {
                    session.removeAttribute("person");
                    currentUser = new Owner(tempUsername, tempPassword);
                    session.setAttribute("person", currentUser);
                }
                specificRooms = model.getSpecificRooms(tempUsername);
                printOwnerDetails(response.getWriter());
            }
        } else {
            response.sendRedirect("failedlogin.html");
        }
    }

    private void printOwnerDetails(PrintWriter writer) {
        writer.println("<head>\n" +
                "    <h2>Succesfully logged in as, owner!</h2>\n" +
                "</head>" + "<br/>");
        writer.println("You have; " + specificRooms.size() + " rooms!");
        writer.println("<br/>" + "<br/>");
        for (int i = 0; i < specificRooms.size(); i++) {
            writer.println("Room: " + specificRooms.get(i).getSquareMeters() + " Square meters for " + specificRooms.get(i).getRentPrice() + " rentprice in " + specificRooms.get(i).getLocation());
            writer.println("<br/>" + "<br/>");
        }
        writer.println("<form action=\"/addroom.html\">\n" +
                "    <button type=\"sumbit\">Add room</button>\n" +
                "</form>");
        writer.println("    <form name=\"form\" method=\"post\" action=\"showPersonServlet\">\n" +
                "        <button type=\"search\">Show Persons</button>\n" +
                "    </form>");

    }

    private boolean ValidateLogin(String username, String password) {
        ArrayList<Person> users = model.getPersons();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
