package servlet;

import model.Model;
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
    private ArrayList<Room> specificRooms = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int squareMeters = Integer.parseInt(request.getParameter("squareMeters"));
        int rentPrice = Integer.parseInt(request.getParameter("rentPrice"));
        String location = request.getParameter("location");

        // Die ownernaam moeten we op een of andere manier in een sessie opslaan om hier te gebruiken. Heb nu maar even owner gebruikt.
        model.addRoom(squareMeters, rentPrice, location, "Owner");

        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";

        htmlResponse += "Room added: " + squareMeters + " Square meters, " + rentPrice + " Rent price " + "in " + location;

        htmlResponse += "</html>";
        writer.println(htmlResponse);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
