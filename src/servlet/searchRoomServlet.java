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

@WebServlet("/searchRoomServlet")
public class searchRoomServlet extends HttpServlet {

    private Model model = Model.getInstance();
    private ArrayList<Room> specificRooms = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int squareMeters = Integer.parseInt(request.getParameter("squareMeters"));
        int rentPrice = Integer.parseInt(request.getParameter("rentPrice"));
        String location = request.getParameter("location");

        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";

        specificRooms = model.getSpecificRooms(squareMeters, rentPrice, location);
        htmlResponse += "We found " + specificRooms.size() + " rooms with the following requirements: " + "<br/>" + "<br/>";
        htmlResponse += "At least " + squareMeters + " square meters" + "<br/>";
        htmlResponse += "Below " + rentPrice + " euro" + "<br/>";
        htmlResponse += "In " + location + "<br/>" + "<br/>";

        for (int i = 0; i <specificRooms.size() ; i++) {
            htmlResponse += "Square meters: " + specificRooms.get(i).getSquareMeters() + ", Rent price: " + specificRooms.get(i).getRentPrice() + ", Location: " + specificRooms.get(i).getLocation();
            htmlResponse += "<br/>";
        }

        htmlResponse += "</html>";
        writer.println(htmlResponse);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
