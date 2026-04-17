import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VariableTrackingTest {

    @Test
    void trackVariables() {
        LoopDebug obj = new LoopDebug();
        int result = obj.sum(new int[]{10,20,30,40,50});

        // observe variable changes in debugger
        assertEquals(150, result);
    }
}
