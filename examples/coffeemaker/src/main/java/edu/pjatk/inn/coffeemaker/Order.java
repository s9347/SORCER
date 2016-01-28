package edu.pjatk.inn.coffeemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

/**
 * Created by asus on 28.01.2016.
 */
public interface Order {

    public Order createOrder(Context context) throws RemoteException, ContextException;

    public Context confirmOrder(Context context) throws RemoteException, ContextException;
}
