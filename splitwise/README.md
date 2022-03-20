## Problem

https://workat.tech/machine-coding/practice/splitwise-problem-0kp2yneec2q2

## Requirements

1. Allow user to add expense
2. Allow user to split payment in 3 ways
   a.Equal
   b.Exact
   c.Percent
3. Show balance for all users
4. Show balance for specific user

## Optional

1. Allow way to add additional ways to split payment
2. Allow additional data to be added to an expense
3. Allow easy generation of passbook

## Entities

1. User
   - userId
   - name
   - email
   - mobile
   
2. Expense
   - expenseId: Long
   - expenseType: ExpenseType
   - initiatedBy: userId
   - splitBetweenUsers: List<Long>
   - name: String ----optional
   
3. ExpenseType
   - Equal
   - Exact
   - Percent
   
4. Balance
   - id
   - userId: USERID
   - owesTo: USERID
   - amount: LONG
   

## Service

1. Splitwise Facade service
   - show balance for userId
   - show balance for all users
   - add new expense
   
2. Balance Service
   - get balance by userId
   - get all balance
   
3. Expense Service
   - add new expense
   
4. Split Strategy Interface
   - split Expense E between N users 