package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {
    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream().filter(dish -> dish.getDishId().contentEquals(dishId)).findFirst().get();
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream().filter(dish -> dish.getId().equals(id)).findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        Optional<Dish> savedDish = DataHolder.dishes.stream().filter(c -> c.getId().equals(dish.getId())).findFirst();

        if (savedDish.isEmpty()){
            DataHolder.dishes.add(dish);
        }else{
            int index = DataHolder.dishes.indexOf(savedDish.get());
            DataHolder.dishes.set(index, dish);
        }

        return dish;
    }

    @Override
    public void deleteById(Long id) {
        var object = DataHolder.dishes.stream().filter(dish -> dish.getId().equals(id)).findFirst();
        object.ifPresent(dish -> DataHolder.dishes.remove(dish));
    }
}
