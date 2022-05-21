import java.util.Random;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        RateLimiter rateLimiter = new RateLimiter(1);

        String requestId = "xqf-123";

        while (true){
            if(rateLimiter.allowRequest()){
                System.out.println("Request Allowed!");
            }
            else{
                System.out.println("Request Blocked!");
            }
            long sleepTime = random.nextInt(10) * 100;
            Thread.sleep(sleepTime);
        }
    }
}
