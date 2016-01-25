package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class InventoryTest {
    private final static Logger logger = LoggerFactory.getLogger(InventoryTest.class);

    private CoffeeMaker coffeeMaker;
    private Inventory inventory;
    private Recipe coffee;

    @Before
    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();
        inventory = coffeeMaker.checkInventory();
        coffee = new Recipe();
        coffee.setName("Coffee");
        coffee.setPrice(50);
        coffee.setAmtCoffee(3);
        coffee.setAmtMilk(1);
        coffee.setAmtSugar(1);
        coffee.setAmtChocolate(0);
    }

    @Test
    //Inventory successfully added
    public void addInventory1() {
        assertTrue(coffeeMaker.addInventory(5,3,7,2));
    }

    @Test
    //Cannot add inventory. Units of coffee can not be negative
    public void addInventory2() {
        assertFalse(coffeeMaker.addInventory(-1,0,0,0));
    }

    @Test
    //Cannot add inventory. Units of milk can not be negative
    public void addInventory3() {
        assertFalse(coffeeMaker.addInventory(5,-1,0,0));
    }

    @Test
    //Cannot add inventory. Units of sugar can not be negative
    public void addInventory4() {
        assertFalse(coffeeMaker.addInventory(5,3,-1,0));
    }

    @Test
    //Cannot add inventory. Units of chocolate can not be negative
    public void addInventory5() {
        assertFalse(coffeeMaker.addInventory(5,3,7,-1));
    }

    @Test
    //Please input an integer.
    public void addInventory6() {
        assertFalse(coffeeMaker.addInventory("a",0,0,0));
    }

    @Test
    //Please input an integer.
    public void addInventory7() {
        assertFalse(coffeeMaker.addInventory(5,"a",0,0));
    }

    @Test
    //Please input an integer.
    public void addInventory8() {
        assertFalse(coffeeMaker.addInventory(5,3,"a",0));
    }

    @Test
    //Please input an integer.
    public void addInventory9() {
        assertFalse(coffeeMaker.addInventory(5,3,7,"a"));
    }

    @Test
    //Coffee: 15
    //Milk: 15
    //Sugar: 15
    //Chocolate: 15
    public void checkInventory() {
        assertTrue(
                (inventory.getCoffee() == 15)&&
                (inventory.getMilk() == 15)&&
                (inventory.getSugar() == 15)&&
                (inventory.getChocolate() == 15)
        );
    }
    @Test
    //Your change is 10.
    public void purchaseBeverage1(){
        coffeeMaker.addRecipe(coffee);
        assertEquals(coffeeMaker.makeCoffee(coffee,60),10);
    }

    @Test
    //Your change is 40.
    public void purchaseBeverage2(){
        coffeeMaker.addRecipe(coffee);
        assertTrue(
                (coffeeMaker.makeCoffee(coffee,40) == 40)&&
                (inventory.getCoffee() == 15)&&
                (inventory.getMilk() == 15)&&
                (inventory.getSugar() == 15)&&
                (inventory.getChocolate() == 15)
        );
    }

    @Test
    //Your change is 50.
    public void purchaseBeverage3(){
        Recipe newCoffee = new Recipe();
        newCoffee.setName("Coffee");
        newCoffee.setPrice(50);
        newCoffee.setAmtCoffee(16);
        newCoffee.setAmtMilk(2);
        newCoffee.setAmtSugar(3);
        newCoffee.setAmtChocolate(5);

        coffeeMaker.addRecipe(newCoffee);

        assertEquals(coffeeMaker.makeCoffee(newCoffee,50),50);
    }
}
