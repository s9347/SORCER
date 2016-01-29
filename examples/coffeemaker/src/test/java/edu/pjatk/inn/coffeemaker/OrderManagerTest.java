package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.Machine;
import edu.pjatk.inn.coffeemaker.impl.OrderImpl;
import edu.pjatk.inn.coffeemaker.impl.OrderManagerImpl;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.service.Exertion;
import sorcer.service.Task;

import javax.crypto.Mac;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import static edu.pjatk.inn.coffeemaker.impl.OrderImpl.getOrder;
import static edu.pjatk.inn.coffeemaker.impl.Recipe.getRecipe;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sorcer.co.operator.ent;
import static sorcer.eo.operator.*;

/**
 * Created by asus on 28.01.2016.
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class OrderManagerTest {
    private final static Logger logger = LoggerFactory.getLogger(OrderManagerTest.class);

    private Context espresso, mocha, macchiato, americano;

    private OrderManagerImpl orderManager;

    private Context order1, order2;

    @Before
    public void setUp() throws ContextException {

        espresso = context(ent("name", "espresso"), ent("price", 50),
                ent("amtCoffee", 6), ent("amtMilk", 0),
                ent("amtSugar", 1), ent("amtChocolate", 0));

        mocha  = context(ent("name", "mocha"), ent("price", 100),
                ent("amtCoffee", 8), ent("amtMilk", 1),
                ent("amtSugar", 1), ent("amtChocolate", 2));

        macchiato  = context(ent("name", "macchiato"), ent("price", 40),
                ent("amtCoffee", 7), ent("amtMilk", 1),
                ent("amtSugar", 2), ent("amtChocolate", 0));

        americano  = context(ent("name", "americano"), ent("price", 40),
                ent("amtCoffee", 4), ent("amtMilk", 0),
                ent("amtSugar", 1), ent("amtChocolate", 0));

        orderManager = new OrderManagerImpl();

        Machine machine1 = new Machine();
        machine1.setMachine_id(101);
        Machine machine2 = new Machine();
        machine2.setMachine_id(102);

        ArrayList<Machine> machList1 = new ArrayList<Machine>();
        machList1.add(machine1);
        machList1.add(machine2);

        ArrayList<Machine> machList2 = new ArrayList<Machine>();
        machList2.add(machine1);

        order1 = context(ent("order_id",1.0f), ent("orderManager",orderManager),
                ent("creationDate",new Date()), ent("machineList",machList1),
                ent("confirmed",false), ent("recipe",getRecipe(mocha)));

        order2 = context(ent("order_id",2.0f), ent("orderManager",orderManager),
                ent("creationDate",new Date()), ent("machineList",machList2),
                ent("confirmed",false), ent("recipe",getRecipe(espresso)));

        //orderManager.addOrder(order1);
        //orderManager.addOrder(order2);
    }

    @After
    public void cleanUp() throws Exception {
        Exertion cmt =
                task(sig("deleteRecipes", CoffeeMaking.class),
                        context(parameterTypes(), args()));

        cmt = exert(cmt);
        logger.info("deleted recipes context: " + context(cmt));
    }

    @Test
    public void testContextOrder() throws ContextException {
        assertTrue(getOrder(order1).getOrder_id() == 1.0f);
    }

    @Test
    public void testContextRecipe() throws ContextException {
        assertTrue(getRecipe(mocha).getName() == "mocha");
    }

    @Test
    public void testContextOrderRecipe() throws ContextException {
        assertTrue(getOrder(order1).getRecipe().getName() == "mocha");
    }

    @Test
    public void testCreateOrder() throws ContextException, RemoteException{
        OrderImpl o = new OrderImpl();
        assertTrue(o.createOrder(order1).getOrder_id() == 1.0f);
    }

    @Test
    public void testConfirmOrder() throws Exception {
        //Powinno sie użyć Order.class, czyly interface zamiast implementacji, ale pojawia się problem
        //CacheLoader returned null for key class sorcer.core.signature.NetSignature:*;PROC;interface edu.pjatk.inn.coffeemaker.Order;confirmOrder.
        //Dla wersji OrderImpl.class działa. Mieć to na uwadze przy podobnych problemach.
        Exertion cmt = task(sig("confirmOrder", OrderImpl.class), order1);
        Context out = context(exert(cmt));
        logger.info("job context: " + out);
        assertEquals(value(out, "order/confirmed"), true);
    }
}
