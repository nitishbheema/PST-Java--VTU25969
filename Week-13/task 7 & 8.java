import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

// ---- TEST CLASS ----
class LifecycleDemoTest {

    List<Integer> sharedList; // acts like mock database

    // Runs once before all tests
    @BeforeAll
    static void initAll() {
        System.out.println("BeforeAll → Setup global resources");
    }

    // Runs before each test
    @BeforeEach
    void setUp() {
        sharedList = new ArrayList<>(Arrays.asList(10, 20, 30));
        System.out.println("BeforeEach → New list created: " + sharedList);
    }

    // ---- TEST CASES ----

    @Test
    void testAddElement() {
        sharedList.add(40);
        assertEquals(4, sharedList.size());
    }

    @Test
    void testRemoveElement() {
        sharedList.remove(0);
        assertEquals(2, sharedList.size());
        assertEquals(20, sharedList.get(0));
    }

    @Test
    void testFreshSetupEachTime() {
        // proves @BeforeEach gives fresh data every test
        assertEquals(Arrays.asList(10, 20, 30), sharedList);
    }

    // Runs after each test
    @AfterEach
    void tearDown() {
        sharedList.clear();
        System.out.println("AfterEach → List cleared");
    }

    // Runs once after all tests
    @AfterAll
    static void tearDownAll() {
        System.out.println("AfterAll → Cleanup global resources");
    }
}
