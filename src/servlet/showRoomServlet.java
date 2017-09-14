package servlet;

import model.Model;
import model.Person;
import model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/showRoomServlet")
public class showRoomServlet extends HttpServlet {

    private Model model = Model.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int squareMeters = Integer.parseInt(request.getParameter("squareMeters"));
        int rentPrice = Integer.parseInt(request.getParameter("rentPrice"));
        String location = request.getParameter("location");

        Person p = (Person) request.getSession().getAttribute("person");
        model.addRoom(squareMeters, rentPrice, location, p.getUsername());

        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";

        htmlResponse += "New room added: " + squareMeters + " Square meters, " + rentPrice + " Rent price " + "in " + location + " on the name; " + p.getUsername();

        htmlResponse += "</html>";
        writer.println(htmlResponse);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
