package edu.pjatk.inn.coffeemaker.impl;

import edu.pjatk.inn.coffeemaker.Order;
import edu.pjatk.inn.coffeemaker.OrderManager;
import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;
import static edu.pjatk.inn.coffeemaker.impl.Recipe.getRecipe;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by asus on 28.01.2016.
 */
public class OrderImpl implements Order {
    private float order_id;
    private Date creationDate;
    private boolean confirmed = false;

    private OrderManager orderManager = null;
    private Recipe recipe = null;
    private Drinker drinker = null;
    private ArrayList<Machine> machineList = null;

    public float getOrder_id() {
        return order_id;
    }

    public void setOrder_id(float order_id) {
        this.order_id = order_id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Drinker getDrinker() {
        return drinker;
    }

    public void setDrinker(Drinker drinker) {
        this.drinker = drinker;
    }

    public ArrayList<Machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(ArrayList<Machine> machineList) {
        this.machineList = machineList;
    }

    @Override
    public OrderImpl createOrder(Context context) throws ContextException {
        OrderImpl o = new OrderImpl();
        o.setOrder_id((Float)context.getValue("order_id"));
        o.setCreationDate((Date)context.getValue("creationDate"));
        o.setConfirmed((Boolean)context.getValue("confirmed"));
        o.setDrinker((Drinker)context.getValue("drinker"));
        o.setRecipe((Recipe)context.getValue("recipe"));
        o.setOrderManager((OrderManager)context.getValue("orderManager"));
        o.setMachineList((ArrayList<Machine>)context.getValue("machineList"));
        return o;
    }

    @Override
    public Context confirmOrder(Context context) throws RemoteException, ContextException{
        context.putValue("order/confirmed", true);
        if (context.getReturnPath() != null) {
            context.setReturnValue(true);
        }
        return context;
    }

    static public OrderImpl getOrder(Context context) throws ContextException {
        OrderImpl o = new OrderImpl();
        o.setOrder_id((Float)context.getValue("order_id"));
        o.setCreationDate((Date)context.getValue("creationDate"));
        o.setConfirmed((Boolean)context.getValue("confirmed"));
        o.setDrinker((Drinker)context.getValue("drinker"));
        o.setRecipe((Recipe)context.getValue("recipe"));
        o.setOrderManager((OrderManager)context.getValue("orderManager"));
        o.setMachineList((ArrayList<Machine>)context.getValue("machineList"));
        return o;
    }
}
