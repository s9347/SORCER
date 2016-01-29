package edu.pjatk.inn.coffeemaker.impl;

import edu.pjatk.inn.coffeemaker.Order;

import java.util.ArrayList;

/**
 * Created by asus on 28.01.2016.
 */
public class Machine {

    float machine_id;
    OrderImpl order;


    public float getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(float machine_id) {
        this.machine_id = machine_id;
    }
    public void addOrder(OrderImpl o){
        order=o;
        ArrayList<Machine> m=o.getMachineList();
        if(!m.contains(this)){
            o.addMachine(this);
        }
    }

    public OrderImpl getOrder() {
        return order;
    }
}
