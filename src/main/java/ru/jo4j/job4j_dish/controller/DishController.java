package ru.jo4j.job4j_dish.controller;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.jo4j.job4j_dish.controller.helpers.ControllerHelpers;
import ru.jo4j.job4j_dish.exception.BadRequestException;
import ru.jo4j.job4j_dish.model.Dish;
import ru.jo4j.job4j_dish.service.DishService;

import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;
    private final ControllerHelpers controllerHelpers;

    public DishController(DishService dishService, ControllerHelpers controllerHelpers) {
        this.dishService = dishService;
        this.controllerHelpers = controllerHelpers;
    }

    @GetMapping("/")
    public List<Dish> findAll() {
        return dishService.findAll();
    }

    @PostMapping("/")
    public Dish create(@RequestBody Dish dish) {
        Optional<Dish> existingDishName = dishService.findByName(dish.getName());
        if (existingDishName.isPresent())
            throw new BadRequestException(String.format("Dish name: %s is present",dish.getName()));
        return dishService.create(dish);
    }

    @GetMapping("/{id}")
    public Dish findById(@PathVariable int id) {
        return controllerHelpers.getDishOrThrowException(id);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody Dish dish) {
        controllerHelpers.getDishOrThrowException(id);
        return dishService.update(dish, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        controllerHelpers.getDishOrThrowException(id);
        dishService.deleteById(id);
    }
}
