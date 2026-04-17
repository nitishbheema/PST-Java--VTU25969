import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

// ---- CLASS ----
class SortUtils {

    public int[] sort(int[] arr) {
        if (arr == null) return null;
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy;
    }
}

// ---- TEST ----
class SortAssertTest {

    SortUtils su = new SortUtils();

    @Test
    void sortNormal() {
        assertArrayEquals(new int[]{1,2,3,4}, su.sort(new int[]{4,1,3,2}));
    }

    @Test
    void sortEmpty() {
        assertArrayEquals(new int[]{}, su.sort(new int[]{}));
    }

    @Test
    void sortNull() {
        assertNull(su.sort(null));
    }

    @Test
    void sortSingle() {
        assertArrayEquals(new int[]{7}, su.sort(new int[]{7}));
    }

    @Test
    void sortNegatives() {
        assertArrayEquals(new int[]{-3,-1,0,2}, su.sort(new int[]{0,-1,2,-3}));
    }
}
