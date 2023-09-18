package ru.jo4j.job4j_dish.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jo4j.job4j_dish.controller.helpers.ControllerHelpers;
import ru.jo4j.job4j_dish.exception.BadRequestException;
import ru.jo4j.job4j_dish.exception.NotFoundException;
import ru.jo4j.job4j_dish.model.Dish;
import ru.jo4j.job4j_dish.service.DishService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishControllerTest {

    @Mock
    private DishService dishService;
    @Mock
    private ControllerHelpers controllerHelpers;

    @InjectMocks
    private DishController dishController;

    @Test
    void whenRequestDishListPageAndReturnList() {
        List<Dish> expectDishes = Arrays.asList(
                new Dish(1, "first", "desc"),
                new Dish(2, "second", "desc")
        );

        Mockito.when(dishService.findAll()).thenReturn(expectDishes);

        List<Dish> rsl = dishController.findAll();

        assertEquals(expectDishes, rsl);
    }

    @Test
    void whenCreateNewDishAndReturnOk() {
        Dish dish = new Dish(1, "first", "desc");

        Mockito.when(dishService.findByName(dish.getName())).thenReturn(Optional.empty());
        Mockito.when(dishService.create(dish)).thenReturn(dish);

        Dish createDish = dishController.create(dish);

        assertNotNull(createDish);
        assertEquals(dish, createDish);
    }

    @Test()
    void whenCreateDishAndDishNameIsPresentAndReturnException() {
        Dish firstDish = new Dish(1, "first", "desc");
        Optional<Dish> secondDish = Optional.of(new Dish(2, "first", "desc"));

        Mockito.when(dishService.findByName(firstDish.getName())).thenReturn(Optional.of(firstDish));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            dishController.create(secondDish.get());
        });

        String eName = ex.getMessage();
        assertTrue(ex.getMessage().contains(eName));
    }

    @Test
    void whenAddOneDishAndDeleteReturnZeroSize() {
        Dish dish = new Dish(1, "first", "desc");
        dishController.create(dish);
        Mockito.when(controllerHelpers.getDishOrThrowException(dish.getId())).thenReturn(dish);

        dishController.delete(dish.getId());

        assertEquals(dishController.findAll().size(), 0);
    }

    @Test
    void whenDeleteDishAndTakeException() {
        Mockito.when(controllerHelpers.getDishOrThrowException(9)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> dishController.delete(9));

        // assertThatExceptionOfType(RuntimeException.class)
        //        .isThrownBy(() -> dishController.delete(9))
        //        .withMessageContaining("Dish with id: 9 not found");
        verify(controllerHelpers, times(1)).getDishOrThrowException(9);
        verify(dishService, never()).deleteById(anyInt());

    }

    @Test
    void whenUpdateDish() {
        int id = 1;
        Dish existingDish = new Dish(id, "First Dish", "Description");
        Dish updatedDish = new Dish(id, "Updated Dish", "New Description");

        Mockito.when(controllerHelpers.getDishOrThrowException(id)).thenReturn(existingDish);
        Mockito.when(dishService.update(updatedDish, id)).thenReturn(true);

        boolean result = dishController.update(id, updatedDish);

        assertTrue(result);
    }
}