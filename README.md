# Library Management System

## Purpose
A console Java Application that manages a library. Members can borrow and return books and magazines, and late returns are charged at a fee.

## Main Features
- Load library items from a CSV file at startup
- Search an item by its id
- Borrow an item with availability and loan limit checks
- Return an item and calculate the late fee

## Business Rules
- A student can borrow up to 3 items and staff member 4
- An item already borrowed cannot be borrowed again
- Late fee for books is 0.5 per day and 0.25 per day for magazines

## How to run 
1. Clone the repository
2. Open the project in your IDE
3. Run 'Application.java' from the project root folder(CSV may be relative: src/main/resources/items.csv)

## How to run the tests
Run 'LibraryServiceTest','CsvItemLoaderTest' and 'LoanTest' from the IDE

## Main Classes
| Class | Role |
|---| |---|
| 'LibraryItem' | Abstract parent of all items |
| 'Book', 'Magazine | Concrete items, each having their own late fee |
| 'Member' | Abstract parent of all members |
| 'Student', 'Staff', | Concrete members, each having a loan limit |
| 'LibraryService' | Business logic: borrow, return, search |
| 'ItemLoader', 'CsvItemLoader' | Reads items from a file |
| 'Application' | Starts the program and displays the results |

## Inheritance
- 'Book' and 'Magazine' extend 'LibraryItem'
- Student' and 'Staff' extend 'Member'

They both share the same data (id,title,name,borrowed list) but differ only in one rule each (late fee and loan limit).

## Polymorphism
'LibraryService' and 'Application' both work with 'LibraryItem' and 'Member' and not with the specific subclasses. So when 'calculateLateFee' is being called on an item, the result depends on whether it is really a 'Book' or a 'Magazine'.
'getMaxLoans()' works the same for students and staff.

## Abstract class and interface
- **Abstract classes:** 'LibraryItem'(abstract 'calculateLateFee') and 'Member' (abstract 'getMaxLoans'). I used abstarct classes because the subclasses share fields and code, and a plain "item" or "member" should never be created by itself.
-  **Interface:** 'ItemLoader'.This describes only the "load items from a path", so that a different format such as JSON could later be added.

## Custom exceptions
- 'ItemNotAvailableException': this exception is thrown when the item has already been borrowed.
-  'BorrowLimitExceededException': this is thrown when a member reaches the limit.

## How data is loaded
At startup, 'CsvItemLoader' reads 'src/main/resources/items.csv' line by line skipping the header, splits each line on commas, and creates a 'Book' or a 'Magazine' depending on the first column. Invalid lines are either reported or skipped, also a missing file is handled with a 'try/catch' in 'Application'.

## Technical Decisions
1. **Abstract classes for item and member:** the subclasses share fields, and the parent should not be created directly.
2. **Interface for loading:** the program is not dependent on the CSV format.
3.  **Custom exceptions instead of returning true or false:** a failure in the borrow explains why it failed.
4.  **Business rules in 'LibraryService':** 'Application' only starts the program and display results.

 ## SOLID PRINCIPLES
- **Single Responsibility:** 'CsvItemLoader' reads files, 'LibraryService' holds the rules, 'Application' coordinates, model classes hold data.
- **Open/Closed:** a new item type; for example, Newspaper only needs a subclass of 'LibraryItem' and 'LibraryService' it does not change.
- **Liskov Substitution:** a 'Book' or 'Magazine' can be used anywhere a 'LibraryItem' is, 'Student' or 'Staff' also can be used anywhere 'Member' is expected.
- **Dependency Inversion:** 'Application' uses the 'ItemLoader' interface and not a specific loader class.

