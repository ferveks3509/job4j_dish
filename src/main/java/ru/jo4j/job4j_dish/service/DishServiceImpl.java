package ru.jo4j.job4j_dish.service;

import org.springframework.stereotype.Service;
import ru.jo4j.job4j_dish.model.Dish;
import ru.jo4j.job4j_dish.repository.DishRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> findAll() {
        return (List<Dish>) dishRepository.findAll();
    }

    @Override
    public Optional<Dish> findById(int id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public boolean update(Dish dish, int id) {
        boolean result = false;
        if (dishRepository.existsById(id)) {
            dishRepository.save(dish);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        boolean result = false;
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Dish> findByName(String name) {
        return dishRepository.findByName(name);
    }
}