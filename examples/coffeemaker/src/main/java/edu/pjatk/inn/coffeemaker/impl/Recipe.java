package edu.pjatk.inn.coffeemaker.impl;

import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author   Sarah & Mike
 */
public class Recipe implements Serializable {
	private float recipe_Id;
    private String name;
    private int price;
    private int amtCoffee;
    private int amtMilk;
    private int amtSugar;
    private int amtChocolate;
	private ArrayList<OrderImpl> orders;
    
    public Recipe() {
    	this.name = "";
		this.recipe_Id=0;
    	this.price = 0;
    	this.amtCoffee = 0;
    	this.amtMilk = 0;
    	this.amtSugar = 0;
    	this.amtChocolate = 0;
		this.orders=new ArrayList<OrderImpl>();
    }
   public ArrayList<OrderImpl> getOrders() {
	   return orders;
   }
	public void addOrder(OrderImpl o){
		if (!orders.contains(o)){
			orders.add(o);
			o.setRecipe(this);
		}
	}
    /**
     * Returns the amount of chocolate in the coffee recipe.
	 * @return   Returns the amtChocolate.
	 */
    public Integer getAmtChocolate() {
		return amtChocolate;
	}
    /**
     * Sets the amount of chocolate in the coffee recipe if the amount is positive number.
	 * @param amtChocolate   The amtChocolate to set.
	 */
    public void setAmtChocolate(Object amtChocolate) {
		if(amtChocolate.getClass()==Integer.class) {
			if ((Integer)amtChocolate >= 0) {
				this.amtChocolate = (Integer)amtChocolate;
			}else this.amtChocolate = -1;
		}else this.amtChocolate = -1;
	}
    /**
     * Returns the amount of coffee in the coffee recipe.
	 * @return   Returns the amtCoffee.
	 */
    public Integer getAmtCoffee() {
		return amtCoffee;
	}
    /**
     * Sets the amount of coffee in the coffee recipe if the amount is positive number.
	 * @param amtCoffee   The amtCoffee to set.
	 */
    public void setAmtCoffee(Object amtCoffee) {
		if(amtCoffee.getClass()==Integer.class) {
			if ((Integer)amtCoffee >= 0) {
				this.amtCoffee = (Integer)amtCoffee;
			}else this.amtCoffee = -1;
		}else this.amtCoffee = -1;
	}
    /**
     * Returns the amount of milk in the coffee recipe.
	 * @return   Returns the amtMilk.
	 */
    public Integer getAmtMilk() {
		return amtMilk;
	}
    /**
     * Sets the amount of milk in the coffee recipe if the amount is positive number.
	 * @param amtMilk   The amtMilk to set.
	 */
    public void setAmtMilk(Object amtMilk) {
		if(amtMilk.getClass()==Integer.class) {
			if ((Integer)amtMilk >= 0) {
				this.amtMilk = (Integer) amtMilk;
			}else this.amtMilk = -1;
		}else this.amtMilk = -1;
	}
    /**
     * Returns the amount of sugar in the coffee recipe.
	 * @return   Returns the amtSugar.
	 */
    public Integer getAmtSugar() {
		return amtSugar;
	}
    /**
     * Sets the amount of sugar in the coffee recipe if the amount is positive number.
	 * @param amtSugar   The amtSugar to set.
	 */
    public void setAmtSugar(Object amtSugar) {
		if(amtSugar.getClass()==Integer.class) {
			if ((Integer)amtSugar >= 0) {
				this.amtSugar = (Integer)amtSugar;
			}else this.amtSugar = -1;
		}else this.amtSugar = -1;
	}
    /**
     * Returns the name of the coffee recipe.
	 * @return   Returns the name.
	 */
    public String getName() {
		return name;
	}
    /**
     * Sets the name of the coffee recipe if the name isn't null.
	 * @param name   The name to set.
	 */
    public void setName(Object name) {
		if(name.getClass()==String.class){
    		if((String)name != null) {
    			this.name = (String)name;
    		}
		}

	}
    /**
     * Returns the price of the coffee recipe.
	 * @return   Returns the price.
	 */
    public Integer getPrice() {
		return price;
	}
    /**
     * Sets the price of the coffee recipe if the price is a positive number.
	 * @param price   The price to set.
	 */
    public void setPrice(Object price) {
		if(price.getClass()==Integer.class) {
			if ((Integer) price >= 0) {
				this.price = (Integer) price;
			}else this.price = -1;
		}else this.price = -1;
	}
    /**
     * Returns true if the recipe has the same name as given recipe.
     * @param r  The recipe to compare.
     * @return true if the recipes have the same name; false otherwise.
     */
    public boolean equals(Recipe r) {
        if((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }
	/**
	 * Returns a String object representing this Recipe, equal to its name.
	 * @return a String representation of this Recipe.
	 */
    public String toString() {
    	return name;
    }
	/**
     * Creates the recipe of the coffee from given Context and returns it.
	 * @param context  The context of the recipe.
	 * @return Returns the recipe.
	 */
	static public Recipe getRecipe(Context context) throws ContextException {
		Recipe r = new Recipe();
		r.recipe_Id = (Float)context.getValue("id");
		r.name = (String)context.getValue("name");
		r.price = (Integer)context.getValue("price");
		r.amtCoffee = (Integer)context.getValue("amtCoffee");
		r.amtMilk = (Integer)context.getValue("amtMilk");
		r.amtSugar = (Integer)context.getValue("amtSugar");
		r.amtChocolate = (Integer)context.getValue("amtChocolate");
		r.orders = (ArrayList<OrderImpl>) context.getValue("orders");

		return r;
	}
	/**
     * Creates the context of given recipe and returns it.
	 * @param recipe  The recipe to give.
	 * @return Returns the context of recipe.
	 */
	static public Context getContext(Recipe recipe) throws ContextException {
		Context cxt = new ServiceContext();
		cxt.putValue("id",recipe.getRecipeId());
		cxt.putValue("name", recipe.getName());
		cxt.putValue("price", recipe.getPrice());
		cxt.putValue("amtCoffee", recipe.getAmtCoffee());
		cxt.putValue("amtMilk", recipe.getAmtMilk());
		cxt.putValue("amtSugar", recipe.getAmtSugar());
		cxt.putValue("amtChocolate", recipe.getAmtChocolate());
		cxt.putValue("orders", recipe.getOrders());

		return cxt;
	}

	public void setRecipeId(float r){
		recipe_Id=r;
	}


	public float getRecipeId() {
		return recipe_Id;
	}
}
