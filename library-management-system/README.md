## Problem

https://workat.tech/machine-coding/practice/design-library-management-system-jgjrv8q8b136


## Requirements

- Add library
- Library consists of N racks
- Add book
- A book can have multiple M copies
- One rack must have at most one book copy
- Search book by id or other attributes
- Book copies can be removed
- Show all the books borrowed by a user id
- Allow a user to borrow a book till specific due date
- A user should be allowed to borrow 5 max books
- Automatically assign book to the first available rack based on the condition in point 5


## Optional

- Thread safe
- Additional attributes can be added to a book and the search system

## Entities

- Library
    - books: List<Book>
    - rack: List<Rack>
    
- Book
    - id
    - title: String
    - authors: List<String>
    - publishers: List<String>
    
- BookCopy
    - id
    - bookId
  
- Rack
    - id
    - books: List<Book>
    

- BookBorrowDetails
    - bookId
    - DueDate

## Service

- Book Service
    - Search service
    - bookCopiesMap: Map<BookId, List<BookCopy>>
    - addBook
    - remove book copy
    - assign book copy to rack
    
- RackService
  - create rack
  - assign book
  - remove book
  - find rack
  
- BorrowService
  - userToBookMap: Map<userId, BookBorrowDetails>
  - findBookBorrowedByUser
  - borrowBook

- Search Service
  - authorToBookMap:
  - publisherToBookMap:
  - updateBookCatalog       // will be called by book service when new book is added or old book is removed
  - searchBookByAttribute

- Library Service
    - bookService
    - borrowService
    - searchService
    - add book
    - remove book
    - search book
    - show all books borrowed by user
