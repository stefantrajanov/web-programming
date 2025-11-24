package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class ChefController {
    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping("/listChefs")
    public String listChefs(Model model){
        model.addAttribute("chefs", this.chefService.listChefs());
        return "listChefs";
    }

    @GetMapping("/chefDetails/{id}")
    public String chefDetails(@PathVariable Long id, Model model){
        model.addAttribute("chef", this.chefService.findById(id));
        return "chefDetails";
    }

    @PostMapping("/chefDetails/{id}")
    public String chefDetails(@PathVariable Long id, @RequestParam String dishId){
        this.chefService.addDishToChef(id, dishId);
        return "redirect:/chefDetails/" + id;
    }
}
