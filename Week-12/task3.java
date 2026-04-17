import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class RecommendationEngineTest {

    RecommendationEngine engine = new RecommendationEngine();

    @Test
    void testUserWithNoWatchHistory() {
        User user = new User("user1", new ArrayList<>());

        List<Movie> recommendations = engine.getRecommendations(user);

        assertNotNull(recommendations);
        assertTrue(recommendations.size() >= 5);
    }

    @Test
    void testUserWithOneWatchedMovie() {
        List<Movie> watched = new ArrayList<>();
        watched.add(new Movie("Inception", "Sci-Fi", 5));

        User user = new User("user2", watched);

        List<Movie> recommendations = engine.getRecommendations(user);

        assertNotNull(recommendations);
        assertTrue(recommendations.size() >= 5);
    }

    @Test
    void testUserWithIdenticalRatingsAcrossGenres() {
        List<Movie> watched = new ArrayList<>();
        watched.add(new Movie("Movie1", "Action", 4));
        watched.add(new Movie("Movie2", "Drama", 4));
        watched.add(new Movie("Movie3", "Comedy", 4));

        User user = new User("user3", watched);

        List<Movie> recommendations = engine.getRecommendations(user);

        assertNotNull(recommendations);
        assertTrue(recommendations.size() >= 5);
    }

    @Test
    void testUserWithLargeWatchHistory() {
        List<Movie> watched = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            watched.add(new Movie("Movie" + i, "Genre" + (i % 5), i % 5));
        }

        User user = new User("user4", watched);

        List<Movie> recommendations = engine.getRecommendations(user);

        assertNotNull(recommendations);
        assertTrue(recommendations.size() >= 5);
    }
}
