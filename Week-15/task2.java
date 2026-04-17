import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DebugTest {

    FixedCalculator fc = new FixedCalculator();

    @Test
    void debugExecution() {
        int[] arr = {1,2,3,4,5};

        // Use Step Into / Step Over here in IDE
        int result = fc.sumArray(arr);

        assertEquals(15, result);
    }
}
