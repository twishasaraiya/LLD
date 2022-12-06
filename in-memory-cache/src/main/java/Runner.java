import services.CacheService;
import storage.HashMapStorage;
import strategy.lru.LeastRecentlyUsed;

public class Runner {
    public static void main(String[] args) {
        CacheService cacheService = new CacheService(new HashMapStorage(2), new LeastRecentlyUsed());

        cacheService.put("name", "sunil");

        cacheService.put(117, "roll-number");

        System.out.println(cacheService.get(117));

        cacheService.put("isOk", false);
        System.out.println(cacheService.get("isOk"));
    }
}
