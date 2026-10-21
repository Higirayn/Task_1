package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void constructorStoresType(IngredientType type) {
        assertEquals(type, new Ingredient(type, "ingredient", 10).getType());
    }

    @ParameterizedTest(name = "Ингредиент {0}, цена {1}")
    @CsvSource({"hot sauce, 100", "cutlet, 42.5", "free sauce, 0"})
    void constructorStoresNameAndPrice(String name, float price) {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, price);
        assertAll(() -> assertEquals(name, ingredient.getName()),
                () -> assertEquals(price, ingredient.getPrice(), 0.001f));
    }
}
