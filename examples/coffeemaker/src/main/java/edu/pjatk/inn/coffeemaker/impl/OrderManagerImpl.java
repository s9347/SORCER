package edu.pjatk.inn.coffeemaker.impl;

import edu.pjatk.inn.coffeemaker.Order;
import edu.pjatk.inn.coffeemaker.OrderManager;
import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 28.01.2016.
 */
public class OrderManagerImpl implements OrderManager{
    Map<Float,OrderImpl> orderList = new HashMap<Float, OrderImpl>();
    Map<Float,Drinker> drinkerList = new HashMap<Float, Drinker>();

    public void addOrder(OrderImpl order){
        if(orderList.containsKey(order.getOrder_id())) {
            orderList.put(order.getOrder_id(), order);
            if(order.getOrderManager()!=this)
            order.setOrderManager(this);
        }
    }

    public Map<Float,OrderImpl> getOrders() {
        return orderList;
    }

    public Map<Float,Drinker> getDrinkers() {
        return drinkerList;
    }
    public void addDrinker(Drinker d){
        if (!drinkerList.containsKey(d.getDrinker_id())){
            drinkerList.put(d.getDrinker_id(),d);
            if(d.getOrderManagerImpl() !=this){
                d.setOrderManagerImpl(this);
            }
        }
    }

    @Override
    public Context makeReport(Context context) throws RemoteException, ContextException {
        return null;
    }

    @Override
    public Context findOrder(Context context) throws RemoteException, ContextException {
        Context cxt = new ServiceContext();
        float order_id = (Float)context.getValue("order_id");
        OrderImpl o = orderList.get(order_id);

        cxt.putValue("order_id", o.getOrder_id());
        cxt.putValue("creationDate", o.getCreationDate());
        cxt.putValue("confirmed",o.isConfirmed());
        return cxt;
    }

    @Override
    public Context selectRecipe(Context context) throws RemoteException, ContextException {
        Context cxt = new ServiceContext();
        float recipe_id = (Float)context.getValue("recipe_id");

        return cxt;
    }

    @Override
    public Context selectMachine(Context context) throws RemoteException, ContextException {
        return null;
    }
}
