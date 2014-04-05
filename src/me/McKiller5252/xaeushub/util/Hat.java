package me.McKiller5252.xaeushub.util;

import org.bukkit.inventory.ItemStack;

public class Hat {
	
	    private ItemStack hat_material;
	    private String name;
	    private int cost;

	    public Hat(ItemStack hat_material, String name, int cost){
	        this.hat_material = hat_material;
	        this.name = name;
	        this.cost = cost;
	    }

	    public String getName(){
	        return this.name;
	    }

	    public Integer getCost(){
	        return this.cost;
	    }

	    public ItemStack getHat(){
	        return this.hat_material;
	    }

}
