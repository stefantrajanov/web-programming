package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.jpa.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        return dishRepository.save(new Dish(dishId, name, cuisine, preparationTime));
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Dish dish = dishRepository.findById(id).orElse(null);
        if (dish == null){
            return null;
        }
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);

        return dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

}
