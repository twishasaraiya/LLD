## Cab Booking

- Allow rider to book a cab
  - Location x,y
    
- Distance
   - only upto a max radius assign cab to a rider
    
- Cab sharing is not scope
  
- Only a single type of cab
- Entities: rider, cab

In scope
- Register a rider      
- register driver
- update cab location
- driver switch on/off availability
- book a cab
- fetch rider history
- driver can end the trip

Expectations
- Should run end to end
- extensible
- clean code
- functionally complete


Model
- User
    - name
    - id
      Inheritance
    - Rider
        - 
    - Driver
        - avalability
        - cabId
- Cab
    - location
- Trip
    - start
    - end
    - status: booked / cancelled / in progress / assigned


Classes
- DriverManager - register a driver, name -> driver map 
- UserManager - register a rider, name -> rider 
- TripService - create new trip, assign driver, end trip , user->trip mapping
- LocationService -  track user-> location , driver -> location, chooses best strategy provides nearest driver strategy
  - CabSelectionStrategy


### Requirements
- Users should be able to create an account for them to book vehicles of the below types.
```aidl
      HATCHBACK
      SUV
      SEDAN
      THREEWHEELER
      TRUCK
      VAN
      MOTORCYCLE
      BICYCLE
```

- Admin should be able to add vehicle to inventory. 
- Users should be able to book an available vehicle. 
- Users should be able to scan qrCode of the vehicle and book (walking booking). 
- Users can pick booked vehicle from the available from the designated places. 
- Users should be able to cancel the booking. 
- Users should be able to return the vehicle post usage. 
- Users get invoice for payment post returning the vehicle. 
- Users Should get remainder notification a day before their booking. 
- Users should be able to choose and add devices and services to the vehicles while they book. 
- System should be provide APIs to search for vechicles booked by users by user id and for a particular interval.