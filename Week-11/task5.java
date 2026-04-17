import java.util.*;

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        
        for (Employee e : employees) {
            map.put(e.id, e);
        }

        return dfs(map, id);
    }

    private int dfs(Map<Integer, Employee> map, int id) {
        Employee emp = map.get(id);
        int total = emp.importance;

        for (int subId : emp.subordinates) {
            total += dfs(map, subId);
        }

        return total;
    }
}
