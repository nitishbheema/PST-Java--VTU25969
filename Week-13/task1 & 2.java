import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// -------------------- CALCULATOR --------------------
class Calculator {

    public int add(int a, int b) { return a + b; }

    public int subtract(int a, int b) { return a - b; }

    public double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }
}

// -------------------- BLACK BOX TEST --------------------
class CalculatorBlackBoxTest {

    Calculator calc = new Calculator();

    // ---- add() ----
    @Test void add_twoPositives() { assertEquals(5, calc.add(2, 3)); }
    @Test void add_twoNegatives() { assertEquals(-5, calc.add(-2, -3)); }
    @Test void add_mixed() { assertEquals(0, calc.add(5, -5)); }
    @Test void add_zeros() { assertEquals(0, calc.add(0, 0)); }
    @Test void add_boundary_max() { assertEquals(Integer.MAX_VALUE, calc.add(Integer.MAX_VALUE, 0)); }

    // ---- subtract() ----
    @Test void subtract_positive() { assertEquals(1, calc.subtract(3, 2)); }
    @Test void subtract_negative() { assertEquals(-1, calc.subtract(2, 3)); }
    @Test void subtract_zero() { assertEquals(5, calc.subtract(5, 0)); }

    // ---- divide() ----
    @Test void divide_normal() { assertEquals(2.5, calc.divide(5, 2), 0.0001); }
    @Test void divide_negative() { assertEquals(-2.0, calc.divide(-4, 2), 0.0001); }
    @Test void divide_bothNegative() { assertEquals(2.0, calc.divide(-4, -2), 0.0001); }

    @Test
    void divide_byZero_throws() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}

// -------------------- BRANCH FUNCTION --------------------
class BranchFunction {

    public String classify(int n) {
        if (n == 0) return "zero";
        if (n > 0) {
            if (n % 2 == 0) return "positive-even";
            else return "positive-odd";
        }
        return "negative";
    }
}

// -------------------- WHITE BOX TEST --------------------
class BranchFunctionWhiteBoxTest {

    BranchFunction bf = new BranchFunction();

    @Test void path_zero() { assertEquals("zero", bf.classify(0)); }
    @Test void path_positiveEven() { assertEquals("positive-even", bf.classify(4)); }
    @Test void path_positiveOdd() { assertEquals("positive-odd", bf.classify(3)); }
    @Test void path_negative() { assertEquals("negative", bf.classify(-7)); }
}
