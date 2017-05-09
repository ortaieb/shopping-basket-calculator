package com.ortaieb.exercises.basket.calculator;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 */
public class ShoppingItemPricesTest {

    private final static ShoppingItem test1Item = ShoppingItem.simpleItem("test1", BigDecimal.valueOf(1.0));
    private final static ShoppingItem test2Item = ShoppingItem.simpleItem("test2", BigDecimal.valueOf(2.0));

    private final static List<ShoppingItem> itemList = ImmutableList.of(test1Item, test2Item);

    @Test
    public void shopping_item_prices_return_empty_if_item_code_was_not_found() {
        assertThat(ShoppingItemPrices.emptyList().extractItem("any"), is(Optional.empty()));
    }

    @Test
    public void shopping_item_prices_retrun_wrapped_instance_if_code_found() {
        assertThat(ShoppingItemPrices.fromList(itemList).extractItem("test1"), is(Optional.of(test1Item)));
    }
}
