
# Exercise:
Using Java, write a simple program that calculates the price of a basket of shopping.
Items are presented one at a time, in a list, identified by name - for example "Apple" or "Banana".

Multiple items are present multiple times in the list,
so for example ["Apple", "Apple", "Banana"] is a basket with two apples and one banana.

Items are priced as follows:
- Apples are 35p each
- Bananas are 20p each
- Melons are 50p each, but are available as 'buy one get one free'
- Limes are 15p each, but are available in a 'three for the price of two' offer

Given a list of shopping, calculate the total cost of those items.

# Assumptions:
- shopping list given via text input file passed as argument in command line (see below). 
   valid input files contain one item per row, there is no row number limit
   *Note.* Because there is a very high chance a user will just run without providing an input file first, there is one hardcoded default list used instead to omit a dry run :)
- inventory is a singleton class, placeholder for excercise given data

# Solution
This is a possible answer to the given exercise:
load all items in a list and generate a map of it grouped by item so, for example, the following list:

`["Apple", "Apple", "Banana", "Melon", "Melon", "Lime", "Melon", "Lime", "Lime", "Lime"]`

is mapped to: `basketContents={Lime=4, Apple=2, Melon=3, Banana=1}`

then [com.cmarina.shopping.ShoppingBasket.computePrice()](https://github.com/calinmarina/shopping-basket/blob/master/src/main/java/com/cmarina/shopping/ShoppingBasket.java#L61) will do the rest.

# Running:
``` bash
$git clone https://github.com/calinmarina/shopping-basket.git
$mvn clean package

...

$java -jar target/ShoppingCart-1.0-SNAPSHOT.jar
Using hardcoded shopping basket!
You can create text files with items list and start ShoppingApp with that file as commandline parameter
ShoppingBasket [basketContents={Lime=4, Apple=2, Melon=3, Banana=1}]
2.35
```

# Run jUnit tests included:
``` bash
mvn test
```
