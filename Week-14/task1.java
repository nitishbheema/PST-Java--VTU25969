import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class StressTestSorting {

    @Test
    void testLargeArraySorting() {
        int size = 100_000;
        int[] arr = new Random().ints(size, 0, 1_000_000).toArray();

        Arrays.sort(arr);

        for (int i = 1; i < arr.length; i++) {
            assertTrue(arr[i] >= arr[i - 1]);
        }
    }
}
