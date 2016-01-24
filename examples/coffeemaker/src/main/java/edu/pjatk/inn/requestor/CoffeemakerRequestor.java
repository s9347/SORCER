package edu.pjatk.inn.requestor;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;

import java.io.*;

/**
 * 
 * @author Sarah E. Smith & Mike Sobolewski
 *
 * Starts the console UI for the CoffeeMaker
 */
public class CoffeemakerRequestor {
    private static CoffeeMaker coffeeMaker;
	public static int obecny;
	public static String[] input;

    public static void mainMenu() {
        System.out.println("1. Add a recipe");
        System.out.println("2. Delete a recipe");
        System.out.println("3. Edit a recipe");
        System.out.println("4. Add inventory");
        System.out.println("5. Check inventory");
        System.out.println("6. Make coffee");
        System.out.println("0. Exit\n");
        
        //Get user input
        int userInput = stringToInt(inputOutput("Please press the number that corresponds to what you would like the coffee maker to do."));

        if(userInput == 1) addRecipe();
        if(userInput == 2) deleteRecipe();
        if(userInput == 3) editRecipe();
        if(userInput == 4) addInventory();
        if(userInput == 5) checkInventory();
        if(userInput == 6) makeCoffee();
        if(userInput == 0) System.exit(0);
    }
	public static void addRecipe() {
	    //Read in recipe name
	    String name = inputOutput("\nPlease enter the recipe name: ");
	    
	    //Read in recipe price
	    String priceString = inputOutput("\nPlease enter the recipe price: $");
	    int price = stringToInt(priceString);
	    if(price < 0) {
	    	System.out.println(name + " could not be added. Price can not be negative");
	    	mainMenu();
	    }
	    
	    //Read in amt coffee
	    String coffeeString = inputOutput("\nPlease enter the units of coffee in the recipe: ");
	    int amtCoffee = stringToInt(coffeeString);
	    if(amtCoffee < 0) {
	    	System.out.println(name + " could not be added. Units of coffee can not be negative");
	    	mainMenu();
	    }
	    
	    //Read in amt milk
	    String milkString = inputOutput("\nPlease enter the units of milk in the recipe: ");
	    int amtMilk = stringToInt(milkString);
	    if(amtMilk < 0) {
	    	System.out.println(name + " could not be added. Units of milk can not be negative");
	    	mainMenu();
	    }
	    
	    //Read in amt sugar
	    String sugarString = inputOutput("\nPlease enter the units of sugar in the recipe: ");
	    int amtSugar = stringToInt(sugarString);
	    if(amtSugar < 0) {
	    	System.out.println(name + " could not be added. Units of sugar can not be negative");
	    	mainMenu();
	    }
	    
	    //Read in amt chocolate
	    String chocolateString = inputOutput("\nPlease enter the units of chocolate in the recipe: ");
	    int amtChocolate = stringToInt(chocolateString);
	    if(amtChocolate < 0) {
	    	System.out.println(name + " could not be added. Units of chocolate can not be negative");
	    	mainMenu();
	    }
	    
	    boolean recipeAdded = false;
		Recipe r = new Recipe();
		r.setName(name);
		r.setPrice(price);
		r.setAmtCoffee(amtCoffee);
		r.setAmtMilk(amtMilk);
		r.setAmtSugar(amtSugar);
		r.setAmtChocolate(amtChocolate);
		   
		recipeAdded = coffeeMaker.addRecipe(r);
	    
	    if(recipeAdded) System.out.println(name + " successfully added.\n");
	    else System.out.println(name + " could not be added.\n");
	    
	    mainMenu();
    }
    
    public static void deleteRecipe() {
        Recipe [] recipes = coffeeMaker.getRecipes();
        boolean [] recipeFull = coffeeMaker.getRecipeFull();
        boolean recipe = false;
        for(int i = 0; i < recipeFull.length; i++) {
        	if(recipeFull[i]) {
        		recipe = true;
        	}
        }
        if(recipe) {
	        for(int i = 0; i < recipes.length; i++) {
	            System.out.println((i+1) + ". " + recipes[i].getName());
	        }
	        String recipeToDeleteString = inputOutput("Please select the number of the recipe to delete.");
	        int recipeToDelete = stringToInt(recipeToDeleteString) - 1;
		    if(recipeToDelete < 0) {
		    	mainMenu();
		    }
	        String name = recipes[recipeToDelete].getName();
	        boolean recipeDeleted = coffeeMaker.deleteRecipe(recipes[recipeToDelete]);
	        
	        if(recipeDeleted) System.out.println(name + " successfully deleted.\n");
		    else System.out.println(name + "could not be deleted.\n");
        }
        else {
        	System.out.println("There are no getRecipes to delete\n");
        }
        mainMenu();
    }
    
    public static void editRecipe() {
        Recipe [] recipes = coffeeMaker.getRecipes();
        boolean [] recipeFull = coffeeMaker.getRecipeFull();
        boolean recipe = false;
        for(int i = 0; i < recipeFull.length; i++) {
        	if(recipeFull[i]) {
        		recipe = true;
        	}
        }
        if(recipe) {
	        for(int i = 0; i < recipes.length; i++) {
	            System.out.println((i+1) + ". " + recipes[i].getName());
	        }
	        String recipeToEditString = inputOutput("Please select the number of the recipe to edit.");
	        int recipeToEdit = stringToInt(recipeToEditString) -1;
		    if(recipeToEdit < 0) {
		    	mainMenu();
		    }
	        
	        Recipe oldRecipe = recipes[recipeToEdit];
	        
		    //Read in recipe name
		    String name = inputOutput("\nPlease enter the recipe name: ");
		    
		    //Read in recipe price
		    String priceString = inputOutput("\nPlease enter the recipe price: $");
		    int price = stringToInt(priceString);
		    if(price < 0) {
		    	System.out.println(name + " could not be edited. Price can not be negative");
		    	mainMenu();
		    }
		    
		    //Read in amt coffee
		    String coffeeString = inputOutput("\nPlease enter the units of coffee in the recipe: ");
		    int amtCoffee = stringToInt(coffeeString);
		    if(amtCoffee < 0) {
		    	System.out.println(name + " could not be edited. Units of coffee can not be negative");
		    	mainMenu();
		    }
		    
		    //Read in amt milk
		    String milkString = inputOutput("\nPlease enter the units of milk in the recipe: ");
		    int amtMilk = stringToInt(milkString);
		    if(amtMilk < 0) {
		    	System.out.println(name + " could not be edited. Units of milk can not be negative");
		    	mainMenu();
		    }
		    
		    //Read in amt sugar
		    String sugarString = inputOutput("\nPlease enter the units of sugar in the recipe: ");
		    int amtSugar = stringToInt(sugarString);
		    if(amtSugar < 0) {
		    	System.out.println(name + " could not be edited. Units of sugar can not be negative");
		    	mainMenu();
		    }
		    
		    //Read in amt chocolate
		    String chocolateString = inputOutput("\nPlease enter the units of chocolate in the recipe: ");
		    int amtChocolate = stringToInt(chocolateString);
		    if(amtChocolate < 0) {
		    	System.out.println(name + " could not be edited. Units of chocolate can not be negative");
		    	mainMenu();
		    }
		    
		    Recipe newRecipe = new Recipe();
		    newRecipe.setName(name);
		    newRecipe.setPrice(price);
		    newRecipe.setAmtCoffee(amtCoffee);
		    newRecipe.setAmtMilk(amtMilk);
		    newRecipe.setAmtSugar(amtSugar);
		    newRecipe.setAmtChocolate(amtChocolate);
	        
	        boolean recipeEdited = coffeeMaker.editRecipe(oldRecipe, newRecipe);
	        
	        if(recipeEdited) System.out.println(oldRecipe.getName() + " successfully edited.\n");
		    else System.out.println(oldRecipe.getName() + "could not be edited.\n");
        }
        else {
        	System.out.println("There are no getRecipes to edit.\n");
        }
        mainMenu();
    }
    
    public static void addInventory() {
	    //Read in amt coffee
	    String coffeeString = inputOutput("\nPlease enter the units of coffee to add: ");
	    int amtCoffee = stringToInt(coffeeString);
	    if(amtCoffee < 0) {
	    	System.out.println("Cannot add inventory. Units of coffee can not be negative");
	    	mainMenu();
	    }
	    
	    //Read in amt milk
	    String milkString = inputOutput("\nPlease enter the units of milk to add: ");
	    int amtMilk = stringToInt(milkString);
	    if(amtMilk < 0) {
	    	System.out.println("Cannot add inventory. Units of milk can not be negative");
	    	mainMenu();
	    }
	    
	    //Read in amt sugar
	    String sugarString = inputOutput("\nPlease enter the units of sugar to add: ");
	    int amtSugar = stringToInt(sugarString);
	    if(amtSugar < 0) {
	    	System.out.println("Cannot add inventory. Units of sugar can not be negative");
	    	mainMenu();
	    }
	    
	    //Read in amt chocolate
	    String chocolateString = inputOutput("\nPlease enter the units of chocolate to add: ");
	    int amtChocolate = stringToInt(chocolateString);
	    if(amtChocolate < 0) {
	    	System.out.println("Cannot add inventory. Units of chocolate can not be negative");
	    	mainMenu();
	    }
	    
        boolean added = coffeeMaker.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);
        if(added) System.out.println("Inventory successfully added");
        else System.out.println("Inventory was not added");
        mainMenu();
    }
    
    public static void checkInventory() {
        Inventory inventory = coffeeMaker.checkInventory();
        System.out.println(inventory.toString());
        mainMenu();
    }
    
    public static void makeCoffee() {
        Recipe [] recipes = coffeeMaker.getRecipes();
        for(int i = 0; i < recipes.length; i++) {
            System.out.println((i+1) + ". " + recipes[i].getName());
        }
        String recipeToPurchaseString = inputOutput("Please select the number of the recipe to purchase.");
        int recipeToPurchase = stringToInt(recipeToPurchaseString) -1;
	    if(recipeToPurchase < 0) {
	    	mainMenu();
	    }
        
        String amountPaid = inputOutput("Please enter the amount you wish to pay");
        int amountToPay = stringToInt(amountPaid);
	    if(amountToPay < 0) {
	    	mainMenu();
	    }
        
        Recipe recipe = recipes[recipeToPurchase];
        int change = coffeeMaker.makeCoffee(recipe, amountToPay);
        
        System.out.println("Your change is: " + change + "\n");
        mainMenu();
    }
    
    public static String inputOutput(String message) {
        System.out.println(message);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String returnString = "";
		ParseNext();
	    try {
	        returnString = br.readLine();
	    }
	    catch (IOException e){
	        System.out.println("Error reading in value");
	        mainMenu();
	    }
	    return returnString;
    }
	public static void ParseNext(){
		StringBufferInputStream brr= new StringBufferInputStream(input[obecny]);
		System.setIn(brr);
		obecny++;
	}
    private static int stringToInt(String value) {
        int returnInt = -1;
        try {
            returnInt = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            System.out.println("Please input an integer\n");
        }
        return returnInt;
    }
    
    public static void main(String[] args) throws IOException {
	    coffeeMaker = new CoffeeMaker();
		checkOptions2();
	}
	public static void checkOptions1() throws IOException {
		input=new String[]{"0"};
		mainMenu();
	}
	public static void checkOptions2() throws IOException {
		input=new String[]{"1"};
		mainMenu();
	}
	public static void checkOptions3() throws IOException {
		input=new String[]{"2"};
		mainMenu();
	}
	public static void checkOptions4() throws IOException {
		input=new String[]{"3"};
		mainMenu();
	}
	public static void checkOptions5() throws IOException {
		input=new String[]{"4"};
		mainMenu();
	}
	public static void checkOptions6() throws IOException {
		input=new String[]{"5"};
		mainMenu();
	}
	public static void checkOptions7() throws IOException {
		input=new String[]{"6"};
		mainMenu();
	}
	public static void addRecipe1() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0"};
		mainMenu();
	}
	public static void addRecipe2() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Coffee","50","3","1","1","0"};
		//nie mozna dodac 2 razy tego samego Recipe
		mainMenu();
	}
	public static void addRecipe3() throws IOException {
		input=new String[]{"1","Mocha","-50"};
		mainMenu();
	}
	public static void addRecipe4() throws IOException {
		input=new String[]{"1","Mocha","60","-3"};
		mainMenu();
	}
	public static void addRecipe5() throws IOException {
		input=new String[]{"1","Mocha","60","3","-2"};
		mainMenu();
	}
	public static void addRecipe6() throws IOException {
		input=new String[]{"1","Mocha","60","3","2","-2"};
		mainMenu();
	}
	public static void addRecipe7() throws IOException {
		input=new String[]{"1","Mocha","60","3","2","2","-3"};
		mainMenu();
	}
	public static void addRecipe8() throws IOException {
		input=new String[]{"1","Mocha","a"};
		mainMenu();
	}

	public static void addRecipe9() throws IOException {
		input=new String[]{"1","Mocha","60","a"};
		mainMenu();
	}
	public static void addRecipe10() throws IOException {
		input=new String[]{"1","Mocha","60","3","a"};
		mainMenu();
	}
	public static void addRecip311() throws IOException {
		input=new String[]{"1","Mocha","60","3","2","a"};
		mainMenu();
	}
	public static void addRecipe12() throws IOException {
		input=new String[]{"1","Mocha","60","3","2","2","a"};
		mainMenu();
	}

	public static void addRecipe13() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3"};
		mainMenu();
	}

	public static void addRecipe14() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0"};
		mainMenu();
	}

	public static void addRecipe15() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","1","Hot Chocolate","60","0","2","2","3"};
		//max 3 napoje
		mainMenu();
	}
	public static void deleteRecipe1() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","2","3"};
		mainMenu();
	}

	public static void deleteRecipe2() throws IOException {
		input = new String[]{"2"};
		//nie ma niczego wiec nie mozna usunac
		mainMenu();
	}

	public static void editRecipe1() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Coffee","50","3","1","1","0"};
		//zamiana recipe 3 na coffee
		mainMenu();
	}
	public static void editRecipe2() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Coffee","50","3","1","1","0","3","3","Coffee","50","3","1","1","0"};
		//zamiana recipe 3 na coffee ponownie co powinno dac blad
		mainMenu();
	}
	public static void editRecipe3() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","-50"};
		mainMenu();
	}
	public static void editRecipe4() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","60","-3"};
		mainMenu();
	}
	public static void editRecipe5() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","60","3","-2"};
		mainMenu();
	}
	public static void editRecipe6() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","60","3","2","-2"};
		mainMenu();
	}
	public static void editRecipe7() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","60","3","2","2","-3"};
		mainMenu();
	}
	public static void editRecipe8() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","a"};
		mainMenu();
	}
	public static void editRecipe9() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","60","a"};
		mainMenu();
	}
	public static void editRecipe10() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","60","3","a"};
		mainMenu();
	}
	public static void editRecipe11() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","60","3","2","a"};
		mainMenu();
	}
	public static void editRecipe12() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","3","3","Mocha","60","3","2","2","a"};
		mainMenu();
	}

	public static void addInventory1() throws IOException {
		input=new String[]{"4","5","3","7","2"};
		mainMenu();
	}

	public static void addInventory2() throws IOException {
		input=new String[]{"4","-1"};
		mainMenu();
	}

	public static void addInventory3() throws IOException {
		input=new String[]{"4","5","-1"};
		mainMenu();
	}

	public static void addInventory4() throws IOException {
		input=new String[]{"4","5","3","-1"};
		mainMenu();
	}

	public static void addInventory5() throws IOException {
		input=new String[]{"4","5","3","7","-1"};
		mainMenu();
	}

	public static void addInventory6() throws IOException {
		input=new String[]{"4","a"};
		mainMenu();
	}

	public static void addInventory7() throws IOException {
		input=new String[]{"4","5","a"};
		mainMenu();
	}

	public static void addInventory8() throws IOException {
		input=new String[]{"4","5","3","a"};
		mainMenu();
	}

	public static void addInventory9() throws IOException {
		input=new String[]{"4","5","3","7","a"};
		mainMenu();
	}
	public static void checkInventory1() throws IOException {
		input=new String[]{"5"};
		mainMenu();
	}
	public static void purchaseBeverage1() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","6","3","60"};
		//dodaj 3 napoje, kup 3 zapłac 60, dostan 10 reszty
		mainMenu();
	}
	public static void purchaseBeverage2() throws IOException {
		input=new String[]{"1","Coffee","50","3","1","1","0","1","Mocha","60","3","2","2","3","1","Latte","60","3","3","2","0","6","3","40","5"};
		//dodaj 3 napoje, kup 3 sprawdz stan inventory
		mainMenu();
	}
	public static void purchaseBeverage3() throws IOException {
		input=new String[]{"1","Coffee","50","16","2","3","5","6","3","50"}; //dodaj Kawe ktorej nie można kupić bo nie ma dostatecznie dużo w inventory
		mainMenu();
	}

}
