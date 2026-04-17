import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class MethodSourceTest {

    @ParameterizedTest
    @MethodSource("data")
    void testLength(String input, int expected) {
        assertEquals(expected, input.length());
    }

    static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of("hello",5),
            Arguments.of("",0),
            Arguments.of("java",4)
        );
    }
}
