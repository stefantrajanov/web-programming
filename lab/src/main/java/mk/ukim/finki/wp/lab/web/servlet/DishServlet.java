package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final DishService dishService;
    private final ChefService chefService;

    public DishServlet(SpringTemplateEngine springTemplateEngine, DishService dishService, ChefService chefService) {
        this.springTemplateEngine = springTemplateEngine;
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object chefId = req.getSession().getAttribute("chefId");

        if (chefId == null) {
            resp.sendRedirect("/listChefs");
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        Long selectedId = Long.parseLong(chefId.toString());

        context.setVariable("chef", chefService.findById(selectedId));
        context.setVariable("dishes", dishService.listDishes());

        springTemplateEngine.process("dishesList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefId = req.getParameter("chefId");

        req.getSession().setAttribute("chefId", chefId);

        resp.sendRedirect("/dish");
    }
}
