package services;

public interface RateLimiter {

    boolean allowRequest();
}
