package ru.jo4j.job4j_dish.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jo4j.job4j_dish.model.Dish;
import ru.jo4j.job4j_dish.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/")
    public List<Dish> findAll() {
        return dishService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Dish> create(Dish dish) {
        return new ResponseEntity<>(
                dishService.create(dish),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> findById(@PathVariable int id) {
        var dish = dishService.findById(id);
        return new ResponseEntity<>(
                dish.orElse(new Dish()),
                dish.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Dish dish) {
        var update = dishService.update(dish, id);
        return new ResponseEntity<>(update ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        var delete = dishService.deleteById(id);
        return new ResponseEntity<>(delete ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
