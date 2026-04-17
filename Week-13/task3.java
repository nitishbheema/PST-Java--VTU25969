import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// ---- CLASS ----
class SimpleMath {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int square(int n) {
        return n * n;
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }
}

// ---- TEST ----
class SimpleMathTest {

    SimpleMath math = new SimpleMath();

    @Test
    void testAdd() {
        assertEquals(7, math.add(3, 4));
    }

    @Test
    void testMultiply() {
        assertEquals(12, math.multiply(3, 4));
    }

    @Test
    void testSquare() {
        assertEquals(25, math.square(5));
    }

    @Test
    void testIsEven() {
        assertTrue(math.isEven(4));
    }

    @Test
    void testIsOdd() {
        assertFalse(math.isEven(7));
    }
}
