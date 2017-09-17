package servlet;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    /*
    Attributes to save a username and password, initialise our dataProvider, arraylist for the rooms, to make a session for the user and a number and dateformat for our cookie.
     */
    private String tempUsername, tempPassword;
    private DataProvider dataProvider;
    private ArrayList<Room> specificRooms;
    private HttpSession session;
    private int number;
    public static final String dateFormat = "yyyy-MM-dd";

    /*
    In the doPost method:
    Saving a username and password by requesting the parameter.
    Getting a session from the request.
    Initialising the dataProvider from the ServletContext
    Generating cookies for the times visited and last visited.
    Checking if a login is validate or not, then we initialise the person in the dataProvider and session.
    We redirect them to either the hirer page or the (by servlet generated) owner page, or to the failed login page.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        tempUsername = request.getParameter("username");
        tempPassword = request.getParameter("password");

        session = request.getSession();

        dataProvider = (DataProvider) getServletContext().getAttribute(DataProvider.class.getName());

        Cookie timesVisited = new Cookie("timesVisited", URLEncoder.encode(Integer.toString(number), "UTF-8"));
        timesVisited.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(timesVisited);
        number++;

        Cookie lastVisited = new Cookie("lastVisited", URLEncoder.encode(getCurrentDate(), "UTF-8"));
        lastVisited.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(lastVisited);

        System.out.println(lastVisited.toString());

        if (ValidateLogin(tempUsername, tempPassword)) {

            if (dataProvider.isHirer(tempUsername)) {
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
            } else if (!dataProvider.isHirer(tempUsername)) {
                Person currentUser = (Person) session.getAttribute("person");
                if (currentUser == null) {
                    currentUser = new Owner(tempUsername, tempPassword);
                    session.setAttribute("person", currentUser);
                } else {
                    session.removeAttribute("person");
                    currentUser = new Owner(tempUsername, tempPassword);
                    session.setAttribute("person", currentUser);
                }
                specificRooms = dataProvider.getSpecificRooms(tempUsername);
                printOwnerDetails(response.getWriter());
            }
        } else {
            response.sendRedirect("failedlogin.html");
        }
    }

    /*
    Generates a whole page for the owner. We are printing all the rooms of the owner.
     */
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
        ArrayList<Person> users = dataProvider.getPersons();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    private static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dataFormat = new SimpleDateFormat(dateFormat);
        return dataFormat.format(calendar.getTime());
    }

}
