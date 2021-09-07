package ua.lysenko.servlets;

import com.google.gson.Gson;
import ua.lysenko.dao.RaceModelDao;
import ua.lysenko.entity.RaceModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;


public class FirstServlet extends HttpServlet {

    RaceModelDao dao = new RaceModelDao();

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
        resp.getWriter().write("Set number of horse with parameter 'horseId'");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<RaceModel> races = dao.getRacesByHorseId(Long.parseLong(req.getParameter("horseId")));
        resp.getWriter().write("Selected horse has participated " + races.size() + " races");
        for (RaceModel model : races) {
            resp.getWriter().write(model.getDate() + "Position: " + model.getPosition());
        }
    }

    @Override
    public void destroy() {
        log("Hasta la vista!");
    }
}
