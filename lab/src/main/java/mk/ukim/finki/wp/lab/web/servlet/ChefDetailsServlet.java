package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ChefDetailsServlet", urlPatterns = "/sschef")
public class ChefDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final ChefService chefService;
    private final DishService dishService;

    public ChefDetailsServlet(SpringTemplateEngine springTemplateEngine, ChefService chefService, DishService dishService) {
        this.springTemplateEngine = springTemplateEngine;
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object chefId = req.getSession().getAttribute("chefId");
        if (chefId == null) {
            resp.sendRedirect("/dish");
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        Chef chef = null;
        try {
            chef = chefService.findById(Long.parseLong(chefId.toString()));
        } catch (Exception e){
            e.printStackTrace();

            resp.sendRedirect("/dish");
            return;
        }

        context.setVariable("chef", chef);

        springTemplateEngine.process("chefDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dishId = req.getParameter("dishId");
        Object chefId = req.getSession().getAttribute("chefId");

        if (dishId == null || chefId == null) {
            resp.sendRedirect("/dish");
            return;
        }

        try {
            Chef chef = chefService.addDishToChef(Long.parseLong(chefId.toString()), dishId);
            req.getSession().setAttribute("chef", chef);
            resp.sendRedirect("/chefDetails");
        } catch (Exception e){
            e.printStackTrace();

            resp.sendRedirect("/dish");
        }
    }
}
