import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

class PerformanceComparisonTest {

    @Test
    void slowMethod() {
        assertTimeout(Duration.ofSeconds(2), () -> {
            long sum = 0;
            for (int i = 0; i < 1_000_000; i++) sum += i;
        });
    }

    @Test
    void optimizedMethod() {
        assertTimeout(Duration.ofMillis(10), () -> {
            long n = 999999;
            long sum = n * (n + 1) / 2;
        });
    }
}
