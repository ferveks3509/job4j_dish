package ru.jo4j.job4j_dish.controller.helpers;

import org.springframework.stereotype.Component;
import ru.jo4j.job4j_dish.exception.NotFoundException;
import ru.jo4j.job4j_dish.model.Dish;
import ru.jo4j.job4j_dish.repository.DishRepository;

@Component
public class ControllerHelpers {
    private final DishRepository dishRepository;

    public ControllerHelpers(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish getDishOrThrowException(int id) {
        return dishRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Dish with id: " + id + " not found"));
    }
}
