package service;

import enums.LockerStatus;
import enums.UserRole;
import model.Locker;
import model.Package;
import model.User;

import javax.naming.AuthenticationException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LockerManagementService {

    private NotificationService notificationService;
    private AuthService authService;
    private LockerService lockerService;
    private LockerAssignmentStrategy lockerAssignmentStrategy;
    private Map<Locker, String> lockerCodeMap;
    private Map<Locker, Package> lockerPackages;

    public LockerManagementService(NotificationService notificationService, AuthService authService, LockerService lockerService) {
        this.notificationService = notificationService;
        this.authService = authService;
        this.lockerService = lockerService;
        this.lockerAssignmentStrategy = new RandomLockerAssignmentStrategy();
        this.lockerCodeMap = new HashMap<>();
        this.lockerPackages = new HashMap<>();
    }

    void assignLockerToUser(User user) throws Exception {
        // check if any locker is available
        List<Locker> availableLocker = lockerService.getLockerByStatus(LockerStatus.FREE);

        if(availableLocker.size() == 0){
            throw new Exception("Locker Not Avaible");
        }

        // assign a locker
        Locker locker = lockerAssignmentStrategy.getLocker(availableLocker);
        // change locker status
        locker.setStatus(LockerStatus.ASSIGNED);

        // generate token
        String token = authService.generateCode(); // String bcz we want code to be immutable
        lockerCodeMap.put(locker, token);

        sendNotification(user, token);
    }

    private void sendNotification(User user, String token){
        // send notification
        notificationService.sendNotification(user, token);
    }

    private Boolean validateCode(Locker locker, String token){
        if(lockerCodeMap.containsKey(locker)){
            return lockerCodeMap.get(locker).equals(token);
        }
        return Boolean.FALSE;
    }

    public void putPackage(Locker locker, User user, Package pkg, String token) throws AuthenticationException {
        // validate user role
        if(!user.getRole().equals(UserRole.DELIVERY_PERSON)){
            throw new AuthenticationException("User is not authorized to put package");
        }
        // validate token
        if(!lockerCodeMap.get(locker).equals(token)){
            throw new InvalidParameterException("Invalid token");
        }
        // put package
        lockerPackages.put(locker, pkg);

        // change locker status
        locker.setStatus(LockerStatus.FULL);

        // get customer
        // this can go to a separate class to get the customer for this locker
        // since there could be a different flow for customers to order package
        // package can contain user info
        User customer = pkg.getUser();
        String code =  authService.generateCode();
        lockerCodeMap.put(locker, code);

        // notify customer
        sendNotification(customer, code);
    }

    public Package getPackage(Locker locker, User user, String token) throws AuthenticationException {
        // validate user role
        if(!user.getRole().equals(UserRole.CUSTOMER)){
            throw new AuthenticationException("Only customers are allowed to get package");
        }
        // validate token
        if(!lockerCodeMap.get(locker).equals(token)){
            throw new InvalidParameterException("Invalid token");
        }

        Package pkg = lockerPackages.get(locker);
        locker.setStatus(LockerStatus.FREE);
        lockerPackages.remove(locker);
        lockerCodeMap.remove(locker);

        return pkg;
    }
}
