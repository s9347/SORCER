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
    private boolean finished = false;


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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean confirmed) {
        this.finished =finished;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public void setOrderManager(OrderManagerImpl orderManager) {

        if (this.orderManager != orderManager) {
            this.orderManager = orderManager;
            if (orderManager.getOrders().containsKey(order_id)) {
                orderManager.addOrder(this);
            }
        }
    }
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        if(!recipe.getOrders().contains(this)){
            recipe.addOrder(this);
        }
    }

    public Drinker getDrinker() {
        return drinker;
    }

    public void setDrinker(Drinker drinker) {

        if(this.drinker!=drinker){
            this.drinker=drinker;
            if(drinker.getOrders().contains(this)) {
                drinker.addOrder(this);
            }
        }
    }

    public ArrayList<Machine> getMachineList() {
        return machineList;
    }

    public void addMachine(Machine m){
        if(!machineList.contains(m)){
            machineList.add(m);
            if(m.getOrder()!=this)
            m.addOrder(this);
        }
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
        o.setOrderManager((OrderManagerImpl) context.getValue("orderManager"));
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
        o.setOrderManager((OrderManagerImpl) context.getValue("orderManager"));
        o.setMachineList((ArrayList<Machine>)context.getValue("machineList"));
        return o;
    }

    public Machine getMachine(float machine_id) {
        int i=0;
        Machine m=null;
        while(i<machineList.size()){
            if(machineList.get(i).getMachine_id()==machine_id){
                m=machineList.get(i);
            }
            i++;
        }
        return m;
    }

    public boolean haveMachineId(float machine_id) {
        int i=0;
        boolean znalazlem=false;
        while(i<machineList.size()) {
            if (machineList.get(i).getMachine_id() == machine_id) znalazlem=true;
            i++;
        }
        return znalazlem;
    }
}
