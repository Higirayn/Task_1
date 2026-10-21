package praktikum;

import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTest {
    @Mock private Bun bun;
    @Mock private Ingredient sauce;
    @Mock private Ingredient filling;
    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    void setsBun() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    void addsIngredientsInOrder() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        assertEquals(List.of(sauce, filling), burger.ingredients);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void removesIngredientByIndex(int index) {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(index);
        assertEquals(List.of(index == 0 ? filling : sauce), burger.ingredients);
    }

    @ParameterizedTest
    @CsvSource({"0, 1", "1, 0"})
    void movesIngredientToNewPosition(int from, int to) {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(from, to);
        assertEquals(List.of(filling, sauce), burger.ingredients);
    }

    @Test
    void priceIncludesTwoBunHalves() {
        when(bun.getPrice()).thenReturn(100.5f);
        burger.setBuns(bun);
        assertEquals(201f, burger.getPrice(), 0.001f);
    }

    @Test
    void priceIncludesEveryIngredient() {
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getPrice()).thenReturn(25.5f);
        when(filling.getPrice()).thenReturn(75f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        assertEquals(300.5f, burger.getPrice(), 0.001f);
    }

    @Test
    void receiptContainsBunIngredientsAndTotal() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(20f);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(30f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        Locale original = Locale.getDefault();
        try {
            Locale.setDefault(Locale.US);
            String expected = String.join(System.lineSeparator(),
                    "(==== white bun ====)", "= sauce hot sauce =", "= filling cutlet =",
                    "(==== white bun ====)", "", "Price: 250.000000", "");
            assertEquals(expected, burger.getReceipt());
        } finally {
            Locale.setDefault(original);
        }
    }
}
