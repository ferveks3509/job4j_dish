package ru.jo4j.job4j_dish.repository;

import org.springframework.data.repository.CrudRepository;
import ru.jo4j.job4j_dish.model.Dish;

import java.util.Optional;

public interface DishRepository extends CrudRepository<Dish, Integer> {
    Optional<Dish> findByName(String name);
}
