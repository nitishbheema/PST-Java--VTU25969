// Task20_LRUCache.java
import java.util.*;

public class Task20_LRUCache {

    static class LRUCache {
        private final int cap;
        private final LinkedHashMap<Integer, Integer> cache;

        public LRUCache(int capacity) {
            this.cap = capacity;
            this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > cap;
                }
            };
        }

        public int get(int key) {
            return cache.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);              // cache: {1=1}
        lRUCache.put(2, 2);              // cache: {1=1, 2=2}
        System.out.println(lRUCache.get(1));   // 1
        lRUCache.put(3, 3);              // evicts key 2, cache: {1=1, 3=3}
        System.out.println(lRUCache.get(2));   // -1
        lRUCache.put(4, 4);              // evicts key 1, cache: {3=3, 4=4}
        System.out.println(lRUCache.get(1));   // -1
        System.out.println(lRUCache.get(3));   // 3
        System.out.println(lRUCache.get(4));   // 4
    }
}
