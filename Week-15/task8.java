import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

// -------------------- EVENT CLASS --------------------
class Event {
    private String userId;
    private String type;

    public Event(String userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    public String getUserId() { return userId; }
    public String getType()   { return type; }
}

// -------------------- LOGGING SERVICE --------------------
class LoggingService {

    private static final Logger logger = LogManager.getLogger(LoggingService.class);
    private static final long RAPID_THRESHOLD_MS = 500;

    private final Map<String, Long> lastEventTime = new HashMap<>();

    public void logEvent(Event event) {

        if (event == null || event.getType() == null) {
            logger.error("System failure: null event received");
            return;
        }

        String userId = event.getUserId();
        long now = System.currentTimeMillis();
        Long lastTime = lastEventTime.get(userId);

        if (lastTime != null && (now - lastTime) < RAPID_THRESHOLD_MS) {
            logger.warn("Rapid events for user [{}] - {}", userId, event.getType());
        } else {
            logger.info("Event logged for user [{}] - {}", userId, event.getType());
        }

        lastEventTime.put(userId, now);
    }
}

// -------------------- TEST CLASS --------------------
class LoggingServiceTest {

    LoggingService svc;

    @BeforeEach
    void setUp() {
        svc = new LoggingService();
    }

    @Test
    void normalEvent_doesNotThrow() {
        assertDoesNotThrow(() -> svc.logEvent(new Event("u1", "play")));
    }

    @Test
    void nullEvent_doesNotThrow() {
        assertDoesNotThrow(() -> svc.logEvent(null));
    }

    @Test
    void nullEventType_doesNotThrow() {
        assertDoesNotThrow(() -> svc.logEvent(new Event("u1", null)));
    }

    @Test
    void rapidEvents_doesNotThrow() {
        assertDoesNotThrow(() -> {
            svc.logEvent(new Event("u2", "play"));
            svc.logEvent(new Event("u2", "skip")); // rapid
        });
    }

    @Test
    void stressTest_manyEvents() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 10_000; i++) {
                svc.logEvent(new Event("user" + (i % 100), "play"));
            }
        });
    }
}
