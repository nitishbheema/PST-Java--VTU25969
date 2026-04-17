import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class PrimeTest {

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    @ParameterizedTest
    @ValueSource(ints = {2,3,5,7,11})
    void testPrime(int n) {
        assertTrue(isPrime(n));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,4,6,9})
    void testNotPrime(int n) {
        assertFalse(isPrime(n));
    }
}
