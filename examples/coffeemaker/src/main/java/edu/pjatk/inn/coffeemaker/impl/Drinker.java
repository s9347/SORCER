package edu.pjatk.inn.coffeemaker.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import edu.pjatk.inn.coffeemaker.OrderManager;

import java.util.ArrayList;

/**
 * Created by asus on 28.01.2016.
 */
public class Drinker {

    float drinker_id = 0;
    String name = "";
    ArrayList<OrderImpl> orders = new ArrayList<OrderImpl>();
    OrderManagerImpl manager= new OrderManagerImpl();

    public float getDrinker_id() {
        return drinker_id;
    }

    public void setDrinker_id(int drinker_id) {
        this.drinker_id = drinker_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<OrderImpl> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderImpl> orders) {
        this.orders = orders;
    }
    public void addOrder(OrderImpl o){
        if(!orders.contains(o)){
            orders.add(o);
            if(o.getDrinker()!=this) {
                o.setDrinker(this);
            }
        }
    }
    public OrderManager getOrderManagerImpl(){
        return manager;
    }
    public void setOrderManagerImpl(OrderManagerImpl m){
        if(manager!=m){
            manager=m;
            if(m.getDrinkers().containsKey(drinker_id)) {
                m.addDrinker(this);
            }
        }
    }

}
