## Hotel Booking System

### Problem Links
- https://www.gohired.in/2020/02/03/system-design-designing-a-lld-for-hotel-booking/
- https://www.gohired.in/2020/02/03/system-design-designing-a-lld-for-hotel-booking/

### Requirements
- User can search hotel by name, location, rating.
- System will have more than 1 hotel. Hotel will have variety of rooms.
- Authenticated user can book 1 or more rooms. 
- For each reservation some sort of payment should be done. The following can be the modes:
  - Partial
  - Full
  - No advance
- Modify the system in such a way that you can accept cancellations based on different refund policies.
- Use lock to handle concurrent request.
- Hotel/Room will have additional facilities. Additional charges will be applied.
  - Facilities
    - LAUNDRY
    - HOT_WATER
    - COMPLIMENTARY_BREAKFAST
    - SWIMMING_POOL
    - DINING