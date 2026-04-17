import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// ---- CLASS ----
class DivideUtils {

    public double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }
}

// ---- TEST ----
class DivideAssertTest {

    DivideUtils du = new DivideUtils();

    @Test
    void divideByZero_throwsException() {
        ArithmeticException ex = assertThrows(
            ArithmeticException.class,
            () -> du.divide(10, 0)
        );
        assertEquals("Division by zero", ex.getMessage());
    }

    @Test
    void divideNormal() {
        assertEquals(2.5, du.divide(5, 2), 0.001);
    }

    @Test
    void divideNegative() {
        assertEquals(-2.0, du.divide(-4, 2), 0.001);
    }
}
