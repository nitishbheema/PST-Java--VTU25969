import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CsvTest {

    @ParameterizedTest
    @CsvSource({
        "1,2,3",
        "2,3,5",
        "5,5,10"
    })
    void testAddition(int a, int b, int result) {
        assertEquals(result, a + b);
    }
}
