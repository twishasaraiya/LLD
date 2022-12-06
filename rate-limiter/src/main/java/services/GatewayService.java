package services;

import model.Request;

import java.util.HashMap;
import java.util.Map;

public class GatewayService {

    private final Map<String, RateLimiter> requestWindowMap = new HashMap<>();

    private static final int TIME_WINDOW = 1;

    private static final int ALLOWED_REQUESTS = 10;

    private static final int REFRESH_RATE = 1;

    private static final int BUCKET_CAPACITY = 10;

    public void processRequest(Request request){
        if(!requestWindowMap.containsKey(request.getId())){
            requestWindowMap.put(request.getId(), new SlidingWindowRateLimiter(TIME_WINDOW, ALLOWED_REQUESTS));
        }
        RateLimiter rateLimiter = requestWindowMap.get(request.getId());
        if(rateLimiter.allowRequest()){
            System.out.println(Thread.currentThread().getName() + " : Request allowed!!");
        }
        else{
            System.out.println(Thread.currentThread().getName() + " : Request not allowed!!");
        }
    }


    public void processRequest1(Request request){
        if(!requestWindowMap.containsKey(request.getId())){
            requestWindowMap.put(request.getId(), new TokenBucketRateLimiter(BUCKET_CAPACITY, REFRESH_RATE));
        }
        RateLimiter rateLimiter = requestWindowMap.get(request.getId());
        if(rateLimiter.allowRequest()){
            System.out.println(Thread.currentThread().getName() + " : Request allowed!!");
        }
        else{
            System.out.println(Thread.currentThread().getName() + " : Request not allowed!!");
        }
    }

}
