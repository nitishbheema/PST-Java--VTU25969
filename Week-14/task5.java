import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class EvenOddTest {

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8})
    void testEven(int n) {
        assertTrue(isEven(n));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,3,5,7})
    void testOdd(int n) {
        assertFalse(isEven(n));
    }
}
