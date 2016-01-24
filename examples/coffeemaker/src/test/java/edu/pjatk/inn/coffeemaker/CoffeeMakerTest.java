package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;
import sorcer.service.Exertion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sorcer.eo.operator.*;

/**
 * @author Mike Sobolewski
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerTest {
	private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

	private CoffeeMaker coffeeMaker;
	private Inventory inventory;
	private Recipe espresso, mocha, macchiato, americano;

	@Before
	public void setUp() throws ContextException {
		coffeeMaker = new CoffeeMaker();
		inventory = coffeeMaker.checkInventory();

		espresso = new Recipe();
		espresso.setName("espresso");
		espresso.setPrice(50);
		espresso.setAmtCoffee(6);
		espresso.setAmtMilk(1);
		espresso.setAmtSugar(1);
		espresso.setAmtChocolate(0);

		mocha = new Recipe();
		mocha.setName("mocha");
		mocha.setPrice(100);
		mocha.setAmtCoffee(8);
		mocha.setAmtMilk(1);
		mocha.setAmtSugar(1);
		mocha.setAmtChocolate(2);

		macchiato = new Recipe();
		macchiato.setName("macchiato");
		macchiato.setPrice(40);
		macchiato.setAmtCoffee(7);
		macchiato.setAmtMilk(1);
		macchiato.setAmtSugar(2);
		macchiato.setAmtChocolate(0);

		americano = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);
	}

	@After
	//Cleanup
	public void cleanUp(){
		coffeeMaker.deleteRecipes();
	}

	@Test
	//Program Exits
	public void checkOptions1() {

	}

	@Test
	//Add Recipe Functionality
	public void checkOptions2() {
		assertTrue(coffeeMaker.addRecipe(espresso));
	}

	@Test
	//Delete Recipe Functionality
	public void checkOptions3() {
		coffeeMaker.addRecipe(espresso);
		assertTrue(coffeeMaker.deleteRecipe(espresso));
	}

	@Test
	//Edit Recipe Functionality
	public void checkOptions4() {
		coffeeMaker.addRecipe(espresso);
		assertTrue(coffeeMaker.editRecipe(espresso,mocha));
	}

	@Test
	//Add Inventory Functionality
	public void checkOptions5() {
		assertTrue(coffeeMaker.addInventory(0,0,0,0));
	}

	@Test
	//Inventory Displays
	public void checkOptions6() {
		assertTrue(coffeeMaker.checkInventory() != null);
	}

	@Test
	//Make Coffee Functionality
	public void checkOptions7() {
		assertTrue(coffeeMaker.makeCoffee(espresso,espresso.getPrice()) == 0);
	}
}

