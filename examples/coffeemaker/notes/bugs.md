### General:

In **CoffeeMaker.java** there is a bug with initialization of the number of recipies.

`NUM_RECIPES = 4`, should be `NUM_RECIPES = 3`.



### Delete a Recipe:

It is a problem with deleting Recipe. It doesn't delete it at all.

In **deleteRecipe(Recipe r)** method there is a line

``` java
recipeArray[i] = recipeArray[i];
```

it should be

``` java
recipeArray[i] =new Recipe(); 
```



### Edit a Recipe:

There is a problem with editing a Recipe. While searching which element of array should be changed, it was choosing newly created element.

In **editRecipe()** method there is a line

``` java
       	if(newRecipe.equals(recipeArray[i])) {
                recipeArray[i] = new Recipe();
            	if(addRecipe(newRecipe)) {
            		canEditRecipe = true;
                    }
```

it should be

``` java
if(oldRecipe.equals(recipeArray[i])) {
					if(this.getRecipeForName(newRecipe.getName())==null) {
						if (newRecipe.getPrice() < 0 || newRecipe.getAmtCoffee() < 0 || newRecipe.getAmtMilk() < 0 || newRecipe.getAmtSugar() < 0 || newRecipe.getAmtChocolate() < 0){
							canEditRecipe = false;
						}
						else{
							recipeArray[i]=newRecipe;
							canEditRecipe = true;
						}
					}
```



### Add Inventory:

There was a bug with adding Inventory. If amount of Sugar was greater than 0, it didn't add  Inventory.

In **addInventory()** method there was a line

``` java
if ((Integer)amtCoffee < 0 || (Integer)amtMilk < 0 || (Integer)amtSugar > 0 || (Integer)amtChocolate < 0) { 
```

it should be

``` java
if ((Integer)amtCoffee < 0 || (Integer)amtMilk < 0 || (Integer)amtSugar < 0 || (Integer)amtChocolate < 0) { 
```



### Purchase Beverage:

There was a bug during purchasing beverage. It didn't set amount of coffee in inventory properly.

In **makeCoffee()** method there was a line

``` java
inventory.setCoffee(inventory.getCoffee() + r.getAmtCoffee());
```

it should be

``` java
inventory.setCoffee(inventory.getCoffee() - r.getAmtCoffee());
```



### Other bugs found:

There is a bug with casting Strings to Integers. If user give the strings while adding an Inventory, it just cast the string to integer and accept it. It should not. For better type control in **addInventory()** method the line 

``` java
public boolean addInventory(int amtCoffee, int amtMilk, int amtSugar, int amtChocolate) {
```

was changed to this line

``` java
public boolean addInventory(Object amtCoffee, Object amtMilk, Object amtSugar, Object amtChocolate) {
```