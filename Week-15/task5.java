import java.util.*;

class MathUtils {

    public int maxSubarraySum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = nums[0], curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            max = Math.max(max, curr);
        }
        return max;
    }

    public int[] prefixSum(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        int[] res = new int[nums.length];
        res[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1] + nums[i];
        }
        return res;
    }

    public Map<Integer,Integer> frequencyCount(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        if (nums == null) return map;

        for (int n : nums) {
            map.put(n, map.getOrDefault(n,0)+1);
        }
        return map;
    }
}
