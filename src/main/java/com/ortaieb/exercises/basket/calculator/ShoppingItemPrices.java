package com.ortaieb.exercises.basket.calculator;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Organise all shopping item instances to allow lookup by item code.
 *
 * The implementation load the items on startup but allow extending the functionality to add/update/delete items
 * from the list by adding service methods.
 */
public class ShoppingItemPrices {

    private Map<String, ShoppingItem> shoppingItems;


    private ShoppingItemPrices(final Map<String, ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }


    public Map<String, ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }


    /**
     * Retrieve Item from shopping item collection.
     *
     * @param code identifier of the item
     * @return ShoppingItem wrapped with optional if found otherwise empty()
     */
    public Optional<ShoppingItem> extractItem(final String code) {
        return getShoppingItems().containsKey(code) ?
               Optional.of(getShoppingItems().get(code)) :
               Optional.empty();
    }


    /**
     * Build Prices object from a list of ShoppingItems
     * @param items list of ShoppingItems to be used in the calculation
     * @return instance of shopping item prices.
     */
    public static ShoppingItemPrices fromList(final List<ShoppingItem> items) {
        return new ShoppingItemPrices(
            items == null ?
            ImmutableMap.of() :
            items.stream().collect(Collectors.toMap(k -> k.getCode(), v -> v)));
    }

    /**
     * Factory for empty ShoppingItemPrices
     * @return empty instance
     */
    public static ShoppingItemPrices emptyList() {
        return new ShoppingItemPrices(ImmutableMap.of());
    }
}
