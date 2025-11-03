package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {
        chefs = new ArrayList<>();
        chefs.add(new Chef(1L, "Marko", "Markovski", "Biography for Marko", new ArrayList<>()));
        chefs.add(new Chef(2L, "Riste", "Ristovski", "Biography for Riste", new ArrayList<>()));
        chefs.add(new Chef(3L, "Petre", "Petrovski", "Biography for Petre", new ArrayList<>()));
        chefs.add(new Chef(4L, "Stefan", "Stefanovski", "Biography for Stefan", new ArrayList<>()));
        chefs.add(new Chef(5L, "Aleksandar", "Aleksov", "Biography for Aleksandar", new ArrayList<>()));

        dishes = new ArrayList<>();
        dishes.add(new Dish("D1", "Pizza", "Fine Dining", 10));
        dishes.add(new Dish("D2", "Swarma", "Middle east", 15));
        dishes.add(new Dish("D3", "Fillet Mignon", "Luxury", 20));
        dishes.add(new Dish("D4", "Giro", "Greek", 5));
        dishes.add(new Dish("D5", "Tavce Gravce", "Macedonian", 40));
    }
}
