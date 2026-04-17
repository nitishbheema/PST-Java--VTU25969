// Task21_ColorfulNumber.java
import java.util.*;

public class Task21_ColorfulNumber {

    public static int colorful(int A) {
        String s = Integer.toString(A);
        Set<Long> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            long product = 1;
            for (int j = i; j < s.length(); j++) {
                product *= (s.charAt(j) - '0');
                if (!seen.add(product)) return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        System.out.println(colorful(A));
    }
}
