package servlet;

import client.RestClient;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RestClientServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        // set up the content type of web page
        response.setContentType("text/html");
        // write a message on the web page
        PrintWriter out = response.getWriter();
        out.println("<h1>" + "REST client for WAS 6.1 test, using Jackson 1.x" + "</h1>");
        out.println("<p>" + RestClient.run() + "</p>");
    }
}
