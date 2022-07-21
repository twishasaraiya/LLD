## Requirements

LLD - Zomato

1. Chain : Delivery executors - orders - payment - restaurant - user
2. Focus on delivery executor - restaurant
3. get status of an order - getOrder(orderId)
   (status - booked, picked up)
   
4. assign delivery executor for a particular order
    - assignDeliveryExecutor(orderId)
    
Any additional classes for above two API
- time to reach 
- if the delivery executor is already occupied dont bother them

## Entities

- Delivery executor
    - id
    - location: x, y
    - time to reach
    - orderId
    - isAvailable

- Restaurant
    - id
    - Queue<Orders>

- Order
    - id
    - status: accepted | cooked | picked up |  completed
    - Location: dest location
  

## API

- /order/{id}/status
- /order/{id}/deliver/assign

## Additional classes

Order Manager
- getStatus(orderId)
    - get order status
      
- updateStatus(orderId)
    - new order -> accepted
    - restaurant -> update status -> cooked
    - delivery executor -> picked up
    - delivery executor -> completed
    
DeliveryManager
- assignDeliveryExecutor(orderId)
    - verify order status is cooked and ready
    - get order location
    - strategy pattern to find the best - currently we can keep random or location based -> returns the time to delivery ( distance(src, dest) / constant speed)
    - return delivery executor
    

    