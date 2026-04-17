import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// ---- CLASS ----
class StringUtils {

    public String reverse(String s) {
        if (s == null) return null;
        return new StringBuilder(s).reverse().toString();
    }

    public boolean isPalindrome(String s) {
        if (s == null) return false;
        String clean = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        return clean.equals(new StringBuilder(clean).reverse().toString());
    }
}

// ---- TEST ----
class StringUtilsTest {

    StringUtils su = new StringUtils();

    @Test
    void testReverse() {
        assertEquals("olleh", su.reverse("hello"));
    }

    @Test
    void testPalindromeTrue() {
        assertTrue(su.isPalindrome("racecar"));
    }

    @Test
    void testPalindromeFalse() {
        assertFalse(su.isPalindrome("hello"));
    }
}
