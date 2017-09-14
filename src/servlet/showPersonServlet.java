package servlet;

import com.sun.org.apache.xpath.internal.operations.Mod;
import model.Hirer;
import model.Model;
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

    private Model model = Model.getInstance();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String htmlRespone = "<html>";

        for (int i = 0; i < model.getPersons().size(); i++) {
            if (model.getPersons().get(i) instanceof Hirer) {
                htmlRespone += "Hirer: " + model.getPersons().get(i).getUsername() + "<br/>";
            }
        }

        htmlRespone += "<br/>";

        for (int i = 0; i <model.getPersons().size() ; i++) {
            if (model.getPersons().get(i) instanceof Owner){
                htmlRespone += "Owner: " + model.getPersons().get(i).getUsername() + "<br/>";
            }
        }

        htmlRespone += "</html>";
        writer.println(htmlRespone);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
