import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RateLimiter {

    private final int requestAllowedPerSec;
    private final ScheduledThreadPoolExecutor scheduledThreadPool;

    private int tokensInBucket;

    public RateLimiter(int requestAllowedPerSec) {
        this.requestAllowedPerSec = requestAllowedPerSec;
        this.tokensInBucket = requestAllowedPerSec;
        this.scheduledThreadPool = new ScheduledThreadPoolExecutor(1);

        scheduledThreadPool.scheduleAtFixedRate(() -> tokensInBucket += requestAllowedPerSec - tokensInBucket, 0, 1000, TimeUnit.MILLISECONDS);
    }


    public boolean allowRequest(){
        if(tokensInBucket > 0){
            tokensInBucket -= 1;
            return true;
        }
        return false;
    }


}
