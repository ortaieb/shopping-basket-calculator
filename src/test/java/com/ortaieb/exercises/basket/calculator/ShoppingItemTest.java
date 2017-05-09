package com.ortaieb.exercises.basket.calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 */
public class ShoppingItemTest {

    @Test(expected = IllegalArgumentException.class)
    public void shopping_item_must_have_non_empty_name() {
        ShoppingItem.simpleItem(null, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shopping_item_must_have_non_negative_price() {
        ShoppingItem.simpleItem("Tester", BigDecimal.valueOf(-0.99));
    }


    @Test(expected = IllegalArgumentException.class)
    public void total_price_of_negative_quantity_throws_illegal_argument_exception() {
        ShoppingItem.simpleItem("Tester", BigDecimal.TEN).totalPrice(-5l);
    }

    @Test
    public void total_price_of_simple_item_is_multiplication_of_input_quantity_and_price() {
        assertThat(ShoppingItem.simpleItem("Tester", BigDecimal.TEN).totalPrice(5l), is(BigDecimal.valueOf(50)));
    }

    @Test
    public void total_price_of_special_sale_will_use_function_over_given_quantity() {
        ShoppingItem buyOneGetOneItem = ShoppingItem.specialSaleItem(
            "Special",
            BigDecimal.valueOf(0.5),
            q -> q / 2 + q % 2
        );
        assertThat(buyOneGetOneItem.totalPrice(5l), is(BigDecimal.valueOf(1.5)));
    }
}
