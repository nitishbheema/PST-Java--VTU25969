import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

// -------------------- LOGIN SERVICE --------------------
class LoginService {

    private Map<String, String> users = new HashMap<>();

    public LoginService() {
        users.put("alice", "pass123");
        users.put("bob",   "qwerty");
    }

    public String login(String username, String password) {
        if (username == null || username.isBlank()) return "ERROR: username required";
        if (password == null || password.isBlank()) return "ERROR: password required";
        if (!users.containsKey(username))           return "ERROR: user not found";
        if (!users.get(username).equals(password))  return "ERROR: wrong password";
        return "SUCCESS: welcome " + username;
    }
}

// -------------------- EDGE CASE TESTS --------------------
class LoginServiceTest {

    LoginService ls = new LoginService();

    // ---- Task 1: Edge Cases ----
    @Test
    void nullUsername() {
        assertEquals("ERROR: username required", ls.login(null, "pass123"));
    }

    @Test
    void nullPassword() {
        assertEquals("ERROR: password required", ls.login("alice", null));
    }

    @Test
    void emptyUsername() {
        assertEquals("ERROR: username required", ls.login("", "pass123"));
    }

    @Test
    void emptyPassword() {
        assertEquals("ERROR: password required", ls.login("alice", ""));
    }

    @Test
    void blankUsername() {
        assertEquals("ERROR: username required", ls.login("   ", "pass123"));
    }

    @Test
    void blankPassword() {
        assertEquals("ERROR: password required", ls.login("alice", "   "));
    }

    @Test
    void singleElementLikeInput() {
        // minimal valid input
        assertEquals("ERROR: user not found", ls.login("a", "b"));
    }

    // ---- Task 2: Login Scenarios ----
    @Test
    void validLogin() {
        assertEquals("SUCCESS: welcome alice", ls.login("alice", "pass123"));
    }

    @Test
    void wrongPassword() {
        assertEquals("ERROR: wrong password", ls.login("alice", "wrong"));
    }

    @Test
    void unknownUser() {
        assertEquals("ERROR: user not found", ls.login("charlie", "pass"));
    }
}
