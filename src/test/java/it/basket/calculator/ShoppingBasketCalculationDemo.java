package it.basket.calculator;

import com.ortaieb.exercises.basket.calculator.ShoppingBasketCalculator;
import com.ortaieb.exercises.basket.calculator.ShoppingItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 */
public class ShoppingBasketCalculationDemo {

    private ShoppingBasketCalculator shoppingBasketCalculator;

    @Before
    public void prepareCalculator() {
        shoppingBasketCalculator = ShoppingBasketCalculator.fromList(
            ShoppingItem.simpleItem("Apple", BigDecimal.valueOf(0.35)),
            ShoppingItem.simpleItem("Banana", BigDecimal.valueOf(0.20)),
            ShoppingItem.specialSaleItem("Melon", BigDecimal.valueOf(0.50), q -> q / 2 + q % 2),
            ShoppingItem.specialSaleItem("Lime", BigDecimal.valueOf(0.15), q -> q / 3 * 2 + q % 3 )
        );
    }

    @Test
    public void empty_basket() {
        assertTrue(shoppingBasketCalculator.basketPrice().compareTo(BigDecimal.valueOf(0)) == 0);
    }

    @Test
    public void simple_basket() {
        assertTrue(shoppingBasketCalculator.basketPrice("Apple", "Apple", "Banana").compareTo(BigDecimal.valueOf(0.9)) == 0);
    }

    @Test
    public void special_sale() {
        final BigDecimal result = shoppingBasketCalculator.basketPrice(
            "Melon", "Melon", "Melon", "Lime", "Lime", "Melon", "Melon", "Lime", "Banana", "Banana", "other fruit");

        assertTrue(result.compareTo(BigDecimal.valueOf(2.20)) == 0);
    }
}
