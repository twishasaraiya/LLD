
### Problem
 Each product has certain Quantity and it will decrease once u take out that product from vending machine.

- Accepting cash & wallet payment
- Select slot -> show price -> Pay or cancel -> Vend the Product 
- Cancel the transaction
- Admin ->Loads the product in Vending Machine[Once in a day]

You need to design a Vending Machine which
- Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.
- Allow user to select products Coke(25), Pepsi(35), Soda(45)
- Return the selected product and remaining change if any - Not handled
- Allow reset operation for vending machine supplier.

https://javarevisited.blogspot.com/2016/06/design-vending-machine-in-java.html

Using State Pattern
https://medium.com/swlh/vending-machine-design-a-state-design-pattern-approach-5b7e1a026cd2

## Final Requirements

- Create a vending machine with predefined row and col - slots
  - A vending machine consists of row x col number of slots
- User selects a slot - row, col 
- Show the price to the user
- User can pay via 2 methods cash or wallet ( Implement cash for phase 1)
- Once payment is done
    - dispense the product
    - update inventory
    - return remaining change if any
    
- User can cancel the transaction before the product is dispensed
- Admin user can load products to the vending machine
  - Admin user can add new product to a empty slot
  - Admin user can change price of a product

## Enums

- Payment Type - Cash | Wallet
- UserRole - Customer, Admin
- CoinType

## Entities

- User
    - name
    - userRole
    
- Vending Machine
    - Slots: Map<Row, Map<Col, Product>>
    
- Slot
    - int row
    - int col
    - Product
    
- Product
    - name
    - price
    - type
    
- Order
    - id
    - product
    - amountPaid
    - timestamp
    - slotNumber - row + col
    - returnedAmount - default 0
    - paidBy - PaymentType
    
  
## Commands

- SELECT SLOT **Row Col**     show price of the product
- PAY UserName CASH Coins[1P 1N 1D 1Q]  pay price for the selected product
  
- CANCEL
- PAY UserName WALLET UNIQUE_ID -- not handled
- LOAD Row Col ProductName Price Username - Admin only
- UPDATE ProductName Price Username - Admin only

## Services

- VendingService - Facade - Can be a Singleton if we are handling just one machine??
  - Select slot
  - Pay
  - load / update products
  
- User Service
  - verify user permissions
  
- Payment Service
   - Cash Payment Service
   - Wallet Payment Service
  - handle payment by type
  
- Slot Service
  - get product from slot
  - dispense product from slot
  - update product from slot  --> Admin only

## Vending Machine States

- SelectState
- PayState
- DispenseState
- CancelState