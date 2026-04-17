import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

// -------------------- ENGINE --------------------
class TopNEngine {

    private static final Logger logger = LogManager.getLogger(TopNEngine.class);

    private final Map<String, Double> itemScores = new HashMap<>();
    private final int n;

    public TopNEngine(int n) {
        this.n = n;
    }

    public void addItem(String name, double score) {
        itemScores.put(name, score);
    }

    public List<String> getTopN() {
        long start = System.currentTimeMillis();

        List<Map.Entry<String, Double>> list = new ArrayList<>(itemScores.entrySet());
        list.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(n, list.size()); i++) {
            result.add(list.get(i).getKey());
        }

        long time = System.currentTimeMillis() - start;
        logger.info("getTopN executed in {} ms", time);

        return result;
    }
}

// -------------------- TEST --------------------
class TopNEngineTest {

    // Task 1: Stress Test (1M items)
    @Test
    void stressTest_millionItems() {
        assertTimeout(Duration.ofSeconds(10), () -> {

            TopNEngine engine = new TopNEngine(10);
            Random rand = new Random();

            for (int i = 0; i < 1_000_000; i++) {
                engine.addItem("item" + i, rand.nextDouble() * 100);
            }

            List<String> top = engine.getTopN();

            assertNotNull(top);
            assertEquals(10, top.size());
        });
    }

    // Task 2: Correctness
    @Test
    void topN_correctness() {
        TopNEngine engine = new TopNEngine(3);

        engine.addItem("A", 9.0);
        engine.addItem("B", 7.5);
        engine.addItem("C", 8.5);
        engine.addItem("D", 6.0);
        engine.addItem("E", 9.5);

        List<String> top = engine.getTopN();

        assertEquals(Arrays.asList("E", "A", "C"), top);
    }

    // Frequent updates
    @Test
    void frequentUpdates() {
        assertTimeout(Duration.ofSeconds(5), () -> {
            TopNEngine engine = new TopNEngine(5);

            for (int i = 0; i < 100_000; i++) {
                engine.addItem("item" + (i % 1000), Math.random() * 100);
                if (i % 100 == 0) engine.getTopN();
            }
        });
    }
}
