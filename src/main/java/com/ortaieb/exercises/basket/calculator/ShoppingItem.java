package com.ortaieb.exercises.basket.calculator;

import com.google.common.base.Function;
import com.google.common.base.Strings;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Shopping item represent a unique item, presented by its identifier, price tag per unit and any special
 * sales calculation.
 */
public class ShoppingItem {

    private final String code;
    private final BigDecimal price;
    private final Function<Long, Long> saleCalc;

    public ShoppingItem(final String code, final BigDecimal price, final Function<Long, Long> saleCalc) {

        validateInput(code, price);

        this.code = code;
        this.price = price;
        this.saleCalc = saleCalc;
    }


    public String getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Function<Long, Long> getSaleCalc() {
        return saleCalc;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingItem)) {
            return false;
        }
        final ShoppingItem that = (ShoppingItem) o;
        return Objects.equals(getCode(), that.getCode()) &&
               Objects.equals(getPrice(), that.getPrice()) &&
               Objects.equals(getSaleCalc(), that.getSaleCalc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getPrice(), getSaleCalc());
    }

    /**
     * Calculation of the price of shopping item.
     * validate quantity is not negative and multiply the price by the calculated number of items.
     *
     * @param quantity given quantity of the specific item in the shopping bag
     *
     * @return total price of the specific item
     */
    public BigDecimal totalPrice(final Long quantity) {
        if (quantity.compareTo(0l) == -1) {
            throw new IllegalArgumentException("quantity can not be negative");
        }

        return getPrice().multiply(BigDecimal.valueOf(saleCalc.apply(quantity)));
    }


    private void validateInput(final String name, final BigDecimal price) {

        if (Strings.isNullOrEmpty(name) || price.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("input must be ([non-empty-code],[non-negative-price])");
        }

    }

    /**
     * Create instance of a simple item.
     * No special manipulation in the price
     *
     * @param code identifier of the item
     * @param price price of a unit
     *
     * @return an instance of shopping item
     *
     */
    public static ShoppingItem simpleItem(final String code, final BigDecimal price) {
        return new ShoppingItem(code, price, q -> q);
    }

    /**
     * Create instance of an item with special calculation of price
     * @param code identifier of the item
     * @param price price of a unit
     * @param salePrice function calculating the number of units to be considered for total price.
     *
     * @return an instance of shopping item
     *
     */
    public static ShoppingItem specialSaleItem(
        final String code, final BigDecimal price, Function<Long, Long> salePrice
    ) {
        return new ShoppingItem(code, price, salePrice);
    }
}
