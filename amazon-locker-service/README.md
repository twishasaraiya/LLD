## Amazon Locker Service

We have an ecommerce website. We want to design a new way to deliver products

Flow
1) Delivery person requests application for a locker
2) System will then assign a locker at random from the available ones
- If we want to consider package attributes like weight , volume in future. it should be extensible
3) That info will be sent to delivery person ie lockerId and a unique code (otp)
4) Delivery person will arrive, put in the code, system will validate it and package will be dropped
5) System will notify the customer will lockerId and another unique code (otp)
6) Customer will arrive, put in the code, system will validate it and package will be picked


# Questions

1. how long is the otp? 
- 4 chars
- can we generate it randomly for now?

2. Are all lockers same?
- For now yes but it can be different in future so the system should be extensible


# Entities

- User
    - id
    - role
        - customer
        - delivery person
    - name
    
- Locker
    - lockerID
    - status
        - free
        - assigned
        - full : contains package
    - assignedTo
        - only if status is assigned

- Package
    - id
    - size
    - weight
    - volume
    
## Class

- LockerManagementService
    - assign available locker - random locker strategy
    - validate locker and code
    - send info to person - notification service
    
- LockerService
  - put package in locker
  - get package from locker
    
- RandomLockerStrategy

- Authentication
    - generate unique code
    - code validation
    
- Notification service
    - send information to user
    
