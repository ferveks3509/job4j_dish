package ru.jo4j.job4j_dish.service;

import ru.jo4j.job4j_dish.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {
    List<Dish> findAll();
    Optional<Dish> findById(int id);
    Dish create(Dish dish);
    boolean update(Dish dish, int id);
    boolean deleteById(int id);
    Optional<Dish> findByName(String name);
}
