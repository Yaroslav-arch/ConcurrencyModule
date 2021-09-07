package ua.lysenko.servlets;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import ua.lysenko.App;
import ua.lysenko.entity.Bet;
import ua.lysenko.entity.Counter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ThirdServlet extends HttpServlet {

    Gson gson = new Gson();
App app = new App();

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
        resp.getWriter().write("Welcome to the Racecourse \n Please place your bet \n");
        resp.getWriter().write("Set quantity of horses with parameter 'quantity'");
        resp.getWriter().write("Set the number of chosen horse with parameter 'chosen'");
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Counter.incrementCounter();
        app.run(gson.fromJson(req.getReader(), Bet.class));
    }

    @Override
    public void destroy() {
        log("Hasta la vista!");
    }
}
