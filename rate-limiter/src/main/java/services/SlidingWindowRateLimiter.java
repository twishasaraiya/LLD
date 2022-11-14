package services;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter {

    private final int timeWindowInSec;
    private final int requestAllowedInWindow;

    private final Queue<Long> window;

    public SlidingWindowRateLimiter(int timeWindowInSec, int requestAllowedInWindow) {
        this.timeWindowInSec = timeWindowInSec;
        this.requestAllowedInWindow = requestAllowedInWindow;
        this.window = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean allowRequest() {

        long currentTime = System.currentTimeMillis();
        adjustWindow(currentTime);
        if(window.size() < requestAllowedInWindow){
            window.offer(currentTime);
            return true;
        }
        return false;
    }

    private void adjustWindow(long currentTime) {

        while(!window.isEmpty() && ((currentTime - window.peek())/1000) >= timeWindowInSec){
            window.poll();
        }
    }
}
