package com.ortaieb.exercises.basket.calculator;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 */
public class ShoppingBasketCalculatorTest {

    private ShoppingBasketCalculator shoppingBasketCalculator;

    @Before
    public void prepareCalculator() {

        shoppingBasketCalculator = ShoppingBasketCalculator.fromList(
            ImmutableList.of(
                ShoppingItem.simpleItem("test1", BigDecimal.valueOf(1.0)),
                ShoppingItem.simpleItem("test2", BigDecimal.valueOf(2.0)),
                ShoppingItem.specialSaleItem("test3", BigDecimal.valueOf(10.0), q -> q * 2)
            )
        );

    }

    @Test
    public void empty_basket_has_total_price_of_zero() {
        assertThat(shoppingBasketCalculator.basketPrice(Arrays.asList()), is(BigDecimal.ZERO));
    }

    @Test
    public void list_with_single_simple_item_will_return_its_price() {
        assertThat(shoppingBasketCalculator.basketPrice(Arrays.asList("test1")), is(BigDecimal.valueOf(1.0)));
        assertThat(shoppingBasketCalculator.basketPrice(Arrays.asList("test2")), is(BigDecimal.valueOf(2.0)));
    }

    @Test
    public void list_of_same_item_will_return_mult_of_price_with_number_of_instances() {
        assertThat(
            shoppingBasketCalculator.basketPrice(Arrays.asList("test1", "test1", "test1", "test1")),
            is(BigDecimal.valueOf(4.0))
        );
    }

    @Test
    public void list_with_unknow_items_return_zero() {
        assertThat(
            shoppingBasketCalculator.basketPrice(Arrays.asList("unknown", "other", "other")),
            is(BigDecimal.ZERO)
        );
    }

    @Test
    public void general_case_of_items() {
        assertThat(
            shoppingBasketCalculator.basketPrice(
                Arrays.asList("unknown", "test1", "test1", "test2", "test3", "test3")),
            is(BigDecimal.valueOf(44.0))
        );
    }
}
