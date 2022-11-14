package service;

public interface AuthService {
    boolean validateCode(int code);
    String generateCode();
}
