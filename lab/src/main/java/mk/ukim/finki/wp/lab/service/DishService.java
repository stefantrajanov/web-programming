package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DishService {
    List<Dish> listDishes();

    Dish findByDishId(String dishId);
}