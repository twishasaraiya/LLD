# Problem

Design an in memory key value store like Redis

https://workat.tech/machine-coding/practice/design-key-value-store-6gz6cq124k65

## Requirements

- Key will always be string
- Value would be an object consisting of attributes
  `Example: "sde_bootcamp": { "title": "SDE-Bootcamp", "price": 30000.00, "enrolled": false, "estimated_time": 30 }`
- Ensure Thread-safety
- Methods required
    - get(key)
    - search(attributeKey, attributeValue)
    - put(key, value)
    - delete(key)
    - keys()
    
- Override toString() method to output attributes in specific format
  `Example: attribute1: attribute_value_1, attribute2: attribute_value_2, attribute3: attribute_value_3`
  
- **TRICKY** The data type of an attribute should get fixed after its first occurrence. Example: Once we encounter an attribute age with an integer value then any entry with an age attribute having a non-integer value should result in an exception.


## Reading Material

- https://www.baeldung.com/java-testing-multithreaded
- https://itsromiljain.medium.com/curious-case-of-concurrenthashmap-90249632d335
- https://mailinator.blogspot.com/2009/06/beautiful-race-condition.html
