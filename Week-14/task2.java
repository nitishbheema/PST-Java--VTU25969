import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ExecutionTimeTest {

    @Test
    void measureTime() {
        int[] arr = new Random().ints(10_000).toArray();

        long start = System.nanoTime();
        Arrays.sort(arr);
        long end = System.nanoTime();

        double time = (end - start) / 1_000_000.0;
        System.out.println("Time: " + time + " ms");

        assertTrue(time < 1000);
    }
}
