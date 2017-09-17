package servlet;

import model.DataProvider;
import model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/searchRoomServlet")
public class searchRoomServlet extends HttpServlet {

    /*
    Attribute for the dataProvider and arraylist for specific rooms.
     */
    private DataProvider dataProvider = DataProvider.getInstance();
    private ArrayList<Room> specificRooms = new ArrayList<>();

    /*
    doPost method;
    Saving the square meters, rent price and location.
    Generating html response to print all the specific rooms with the minimum square meters, maximum rent price and the location.
    Also, we are printing the cookies here, we do this by requesting the cookies with the request.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int squareMeters = Integer.parseInt(request.getParameter("squareMeters"));
        int rentPrice = Integer.parseInt(request.getParameter("rentPrice"));
        String location = request.getParameter("location");

        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>";

        specificRooms = dataProvider.getSpecificRooms(squareMeters, rentPrice, location);
        htmlResponse += "We found " + specificRooms.size() + " room(s) with the following requirements: " + "<br/>" + "<br/>";
        htmlResponse += "At least " + squareMeters + " square meters" + "<br/>";
        htmlResponse += "Below " + rentPrice + " euro" + "<br/>";
        htmlResponse += "In " + location + "<br/>" + "<br/>";

        for (int i = 0; i < specificRooms.size(); i++) {
            htmlResponse += "Square meters: " + specificRooms.get(i).getSquareMeters() + ", Rent price: " + specificRooms.get(i).getRentPrice() + ", Location: " + specificRooms.get(i).getLocation();
            htmlResponse += "<br/>";
        }

        HttpSession httpSession = request.getSession();
        String lastVisited = "lastVisited";
        String timesVisited = "timesVisited";
        String message = "";
        Cookie[] cookieArray = request.getCookies();
        if (cookieArray != null) {
            for (int i = 0; i < cookieArray.length; i++) {
                Cookie currentCookie = cookieArray[i];
                if (lastVisited.equals(currentCookie.getName())) {
                    message = message + "Last time visited: " + currentCookie.getValue() + "<br/>";
                }
                if (timesVisited.equals(currentCookie.getName())) {
                    if (Integer.parseInt(currentCookie.getValue()) == 0) {
                        message = message + "<br/>";
                        message = message + "You are the first visitor!" + "<br/>";
                        currentCookie.setValue(currentCookie.getValue() + 1);
                    } else {
                        message = message + "<br/>";
                        message = message + "The page has been visited; " + currentCookie.getValue() + " time(s) " + "<br/>";
                        currentCookie.setValue(currentCookie.getValue() + 1);
                    }
                }

            }

            htmlResponse += message;

            htmlResponse += "</html>";
            writer.println(htmlResponse);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
