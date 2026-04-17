import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    MathUtils mu = new MathUtils();

    @Test void emptyArray() {
        assertEquals(0, mu.maxSubarraySum(new int[]{}));
    }

    @Test void negativeValues() {
        assertEquals(-1, mu.maxSubarraySum(new int[]{-3,-1,-2}));
    }

    @Test void positiveValues() {
        assertEquals(6, mu.maxSubarraySum(new int[]{1,2,3}));
    }

    @Test void prefixTest() {
        assertArrayEquals(new int[]{1,3,6}, mu.prefixSum(new int[]{1,2,3}));
    }

    @Test void frequencyTest() {
        assertEquals(2, mu.frequencyCount(new int[]{1,1,2}).get(1));
    }

    @Test
    void stressTest() {
        assertTimeout(Duration.ofSeconds(3), () -> {
            int[] big = new int[100000];
            Arrays.fill(big, 1);
            mu.prefixSum(big);
        });
    }
}
