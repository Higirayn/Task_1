package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class BunTest {
    @ParameterizedTest(name = "Булка {0}, цена {1}")
    @CsvSource({"black bun, 100", "white bun, 200.5", "free bun, 0"})
    void constructorStoresNameAndPrice(String name, float price) {
        Bun bun = new Bun(name, price);
        assertAll(() -> assertEquals(name, bun.getName()),
                () -> assertEquals(price, bun.getPrice(), 0.001f));
    }
}
