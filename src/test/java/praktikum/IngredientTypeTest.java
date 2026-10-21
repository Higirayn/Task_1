package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {
    @Test
    void containsSauceAndFilling() {
        assertArrayEquals(new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                IngredientType.values());
    }

    @ParameterizedTest
    @CsvSource({"SAUCE, SAUCE", "FILLING, FILLING"})
    void findsTypeByName(String name, IngredientType expected) {
        assertEquals(expected, IngredientType.valueOf(name));
    }
}
