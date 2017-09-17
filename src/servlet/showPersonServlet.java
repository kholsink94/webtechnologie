package servlet;

import model.Hirer;
import model.DataProvider;
import model.Owner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/showPersonServlet")
public class showPersonServlet extends HttpServlet {
    /*
    Attributes for the dataProvider.
     */
    private DataProvider dataProvider = DataProvider.getInstance();

    /*
    doPost method, we have no post actions so we are not using this.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /*
    doGet method, we are checking the person list in our dataProvider.
    Printing all the persons in a html response with the writer.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String htmlRespone = "<html>";

        for (int i = 0; i < dataProvider.getPersons().size(); i++) {
            if (dataProvider.getPersons().get(i) instanceof Hirer) {
                htmlRespone += "Hirer: " + dataProvider.getPersons().get(i).getUsername() + "<br/>";
            }
        }

        htmlRespone += "<br/>";

        for (int i = 0; i < dataProvider.getPersons().size(); i++) {
            if (dataProvider.getPersons().get(i) instanceof Owner) {
                htmlRespone += "Owner: " + dataProvider.getPersons().get(i).getUsername() + "<br/>";
            }
        }

        htmlRespone += "</html>";
        writer.println(htmlRespone);
    }
}
