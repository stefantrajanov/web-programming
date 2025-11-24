package mk.ukim.finki.wp.lab.web.controller;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.ui.Model;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping()
    public String getDishesPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("dishes", this.dishService.listDishes());
        return "listDishes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id){
        this.dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form")
    public String addDish(Model model){
        return "dish-form";
    }

    @PostMapping("/add")
    public String addDish(
            @RequestParam String dishId,
            @RequestParam String name,
            @RequestParam String cuisine,
            @RequestParam int preperationTime

    ){
        this.dishService.create(dishId, name, cuisine, preperationTime);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form/{id}")
    public String editDish(@PathVariable Long id, Model model){
        Dish dish = this.dishService.findById(id);
        model.addAttribute("dish", dish);

        return "dish-form";
    }

    @PostMapping("/edit/{id}")
    public String editDish(
            @PathVariable Long id,
            @RequestParam String dishId,
            @RequestParam String name,
            @RequestParam String cuisine,
            @RequestParam int preperationTime

    ){
        this.dishService.update(id, dishId, name, cuisine, preperationTime);
        return "redirect:/dishes";
    }

    @GetMapping("add-dish-to-chef")
    public String addDishToChef(
            @RequestParam Long chefId,
            Model model
    ){
        model.addAttribute("chef", chefService.findById(chefId));
        model.addAttribute("dishes", dishService.listDishes());

        return "dishesList";
    }
}
