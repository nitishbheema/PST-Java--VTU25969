import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// -------------------- CLASS --------------------
class UserAnalytics {

    static class User {
        String name;
        int age;
        String location;
        double activityScore;
        boolean isActive;

        public User(String name, int age, String location,
                    double activityScore, boolean isActive) {
            this.name = name;
            this.age = age;
            this.location = location;
            this.activityScore = activityScore;
            this.isActive = isActive;
        }
    }

    // FIXED METHOD
    public double getAverageActivityScore(List<User> users) {
        if (users == null || users.isEmpty()) return 0.0;

        return users.stream()
                .filter(u -> u.isActive)
                .mapToDouble(u -> u.activityScore)
                .average()
                .orElse(0.0);
    }

    public List<User> filterActiveUsers(List<User> users) {
        if (users == null) return new ArrayList<>();

        return users.stream()
                .filter(u -> u.isActive)
                .collect(Collectors.toList());
    }
}

// -------------------- TEST --------------------
class UserAnalyticsTest {

    UserAnalytics ua = new UserAnalytics();

    List<UserAnalytics.User> sampleUsers = Arrays.asList(
        new UserAnalytics.User("Alice", 25, "Chennai", 8.5, true),
        new UserAnalytics.User("Bob", 30, "Mumbai", 6.0, false),
        new UserAnalytics.User("Carol", 22, "Delhi", 9.0, true),
        new UserAnalytics.User("Dave", 28, "Bangalore", 7.5, true)
    );

    @Test
    void averageScore_normalUsers() {
        double avg = ua.getAverageActivityScore(sampleUsers);
        assertEquals((8.5 + 9.0 + 7.5) / 3.0, avg, 0.001);
    }

    @Test
    void averageScore_emptyList() {
        assertEquals(0.0, ua.getAverageActivityScore(new ArrayList<>()));
    }

    @Test
    void averageScore_nullList() {
        assertEquals(0.0, ua.getAverageActivityScore(null));
    }

    @Test
    void averageScore_allInactive() {
        List<UserAnalytics.User> users = Arrays.asList(
            new UserAnalytics.User("X", 20, "A", 5.0, false),
            new UserAnalytics.User("Y", 21, "B", 6.0, false)
        );

        assertEquals(0.0, ua.getAverageActivityScore(users));
    }

    @Test
    void filterActive_returnsOnlyActive() {
        List<UserAnalytics.User> active = ua.filterActiveUsers(sampleUsers);

        assertEquals(3, active.size());
        active.forEach(u -> assertTrue(u.isActive));
    }
}
