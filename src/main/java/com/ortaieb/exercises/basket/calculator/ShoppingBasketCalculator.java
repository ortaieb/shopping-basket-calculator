package com.ortaieb.exercises.basket.calculator;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 */
public class ShoppingBasketCalculator {

    private ShoppingItemPrices shoppingItemPrices;


    public ShoppingBasketCalculator(final ShoppingItemPrices shoppingItemPrices) {
        this.shoppingItemPrices = shoppingItemPrices;
    }


    public ShoppingItemPrices getShoppingItemPrices() {
        return shoppingItemPrices;
    }


    public BigDecimal basketPrice(final String... itemCodes) {
        return basketPrice(Arrays.asList(itemCodes));
    }

    /**
     *
     * @param itemCodes
     * @return
     */
    public BigDecimal basketPrice(final List<String> itemCodes) {

        return groupItems(itemCodes).entrySet()
                                    .stream()
                                    .map(e -> e.getKey().totalPrice(e.getValue()))
                                    .reduce((p1, p2) -> p1.add(p2))
                                    .orElse(BigDecimal.ZERO);

    }

    private Map<ShoppingItem, Long> groupItems(final List<String> itemCodes) {
        return itemCodes == null ?
               ImmutableMap.of() :
               itemCodes.stream()
                        .map(e -> getShoppingItemPrices().extractItem(e))
                        .filter(e -> e.isPresent())
                        .collect(Collectors.groupingBy(k -> k.get(), Collectors.counting()));
    }


    public static ShoppingBasketCalculator fromList(final List<ShoppingItem> items) {
        return new ShoppingBasketCalculator(ShoppingItemPrices.fromList(items));
    }

    public static ShoppingBasketCalculator fromList(final ShoppingItem... items) {
        return fromList(Arrays.asList(items));
    }
}
