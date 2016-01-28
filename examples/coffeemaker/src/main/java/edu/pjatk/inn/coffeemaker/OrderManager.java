package edu.pjatk.inn.coffeemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

/**
 * Created by asus on 28.01.2016.
 */
public interface OrderManager {

    public Context makeReport(Context context) throws RemoteException, ContextException;

    public Context findOrder(Context context) throws RemoteException, ContextException;

    public Context selectRecipe(Context context) throws RemoteException, ContextException;

    public Context selectMachine(Context context) throws RemoteException, ContextException;
}
