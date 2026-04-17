// Task22_MeetingRooms.java
import java.util.*;

public class Task22_MeetingRooms {

    public static int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> timeline = new TreeMap<>();
        for (int[] interval : intervals) {
            timeline.merge(interval[0],  1, Integer::sum); // meeting starts
            timeline.merge(interval[1], -1, Integer::sum); // meeting ends
        }
        int rooms = 0, maxRooms = 0;
        for (int delta : timeline.values()) {
            rooms += delta;
            maxRooms = Math.max(maxRooms, rooms);
        }
        return maxRooms;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }
        System.out.println(minMeetingRooms(intervals));
    }
}
