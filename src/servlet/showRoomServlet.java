package servlet;

import model.DataProvider;
import model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/showRoomServlet")
public class showRoomServlet extends HttpServlet {

    /*
    Attribute for the dataProvider.
     */
    private DataProvider dataProvider = DataProvider.getInstance();

    /*
    doPost method, we have no post in this application.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /*
    Saving the square meters, rent price and location to print it in the htmlresponse.
    Adding this person to the dataProvider.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int squareMeters = Integer.parseInt(request.getParameter("squareMeters"));
        int rentPrice = Integer.parseInt(request.getParameter("rentPrice"));
        String location = request.getParameter("location");

        Person p = (Person) request.getSession().getAttribute("person");
        dataProvider.addRoom(squareMeters, rentPrice, location, p.getUsername());

        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";

        htmlResponse += "New room added: " + squareMeters + " Square meters, " + rentPrice + " Rent price " + "in " + location + " on the name; " + p.getUsername();

        htmlResponse += "</html>";
        writer.println(htmlResponse);
    }
}
