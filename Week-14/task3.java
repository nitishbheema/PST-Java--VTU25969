import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

class TimeoutTest {

    @Test
    void testTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            int sum = 0;
            for (int i = 0; i < 10000; i++) sum += i;
        });
    }
}
