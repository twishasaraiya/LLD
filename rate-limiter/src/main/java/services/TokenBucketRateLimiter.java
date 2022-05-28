package services;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketRateLimiter implements RateLimiter {

    private final int bucketCapacity;
    private final int refreshRate;

    private AtomicInteger currentCapacity;

    private AtomicLong lastUpdatedTime;

    public TokenBucketRateLimiter(int bucketCapacity, int refreshRate) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.currentCapacity = new AtomicInteger(bucketCapacity);
        this.lastUpdatedTime = new AtomicLong(System.currentTimeMillis());
    }

    @Override
    public boolean allowRequest() {
        refillBucket();
        if(currentCapacity.get() > 0){
            currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }

    private void refillBucket() {
        long currentTime = System.currentTimeMillis();
        int tokensToAdd = (int) (((currentTime - lastUpdatedTime.get())/1000) * refreshRate);
        int newCapacity = Math.min(currentCapacity.get() + tokensToAdd, bucketCapacity);
        currentCapacity.getAndSet(newCapacity);
        lastUpdatedTime.getAndSet(currentTime);
    }
}
