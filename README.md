# Shopping Basket Price Calculator

Submitted by: Or Taieb <or.taieb@gmail.com>

## Task Description

```
Using Java, write a simple program that calculates the price of a basket of shopping.
Items are presented one at a time, in a list, identified by code - for example "Apple" or "Banana".
Multiple items are present multiple times in the list, 
so for example ["Apple", "Apple", "Banana"] is a basket with two apples and one banana.

Items are priced as follows:
- Apples are 35p each
- Bananas are 20p each
- Melons are 50p each, but are available as ‘buy one get one free’
- Limes are 15p each, but are available in a ‘three for the price of two’ offer

Given a list of shopping, calculate the total cost of those items.
```

## Solution

### Structure and execution
This is a simple gradle project. executing `./gradlew clean build` will recreate, compile run the 
 tests and generate jar file with its content.
Sub path ./src/main/java contain implementation of the solution.
Under path ./src/test/java/com/... you can find unit tests, while under ./src/test/java/it you can 
find integration test reflecting the price list and special prices calculations as mentioned in the 
task description.

*ShoppingBasketCalculationDemo* represent how the application should use the calculator.

### Proposed solution used the following assumptions

* Name of item is unique.
* Input validation ignores unknown item codes (alternatively could reject the whole list)
* Item List is static for the exercise but can be extended by adding relevant interface (add,remove,update) 
 to class ShoppingItemPrices.
* On a real system Item List should be kept in a dynamic form and loaded (resource/file) to the system, For the 
 scope of the exercise this part was not implemented and regarded in the integration tests as hard coded init.
* To handle Collections and object validation the solution depends on guava.