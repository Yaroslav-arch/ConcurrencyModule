package ua.lysenko.servlets;

import com.google.gson.Gson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;


public class SecondServlet extends HttpServlet {

    Gson gson = new Gson();


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("It is alive!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write(gson.toJson(visits));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Visit visit = gson.fromJson(req.getReader(), Visit.class);
        while (visits.size()>=3){
            visits.remove();
        }
        visits.add(visit);
    }

    @Override
    public void destroy() {
        log("Hasta la vista!");
    }
}
