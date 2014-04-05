package me.McKiller5252.xaeushub.shop;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class XaeusShopGui implements Listener{
	
	private Inventory hat;
	
	private ItemStack a, b;
	private ItemStack z;
	
	public ChatColor gre = ChatColor.GREEN;
	
	 int Miner = 100;
	 int Cob = 1000;
	 int Remove = 0;
	
	public XaeusShopGui(Plugin m){
		hat = Bukkit.getServer().createInventory(null, 27, ChatColor.YELLOW + "Xaeus Hat Shop");
		
		//All Hats
		a = createItem(Material.DIAMOND_ORE, gre + "Miner's Hat", Miner);
		b = createItem(Material.WEB, gre + "Spiderman Hat", Cob);
		
		
		hat.setItem(0, a);
		hat.setItem(1, b);
		
		
		//Clear Hat
		z = createItem(Material.WOOL, ChatColor.GOLD + "Click to remove your Hat!", Remove);
		hat.setItem(22, z);
		
		
	   Bukkit.getServer().getPluginManager().registerEvents(this, m);
	}
	
	private ItemStack createItem(Material dc, String name, int cost){
		ItemStack i = new ItemStack(dc);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("§7Cost: §6" + cost));
		i.setItemMeta(im);
		return i;
	}
	
	public void show(Player p) {
		p.openInventory(hat);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void click(InventoryClickEvent e){
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals(Material.AIR)){
			
		if (e.getInventory().getSize() == 27) {
			e.setCancelled(true);
			
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Miner's Hat")){
			p.getInventory().setHelmet(a);
			p.closeInventory();
		}
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Spiderman Hat")){
			p.getInventory().setHelmet(b);
			p.closeInventory();
		}
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Click to remove your Hat!")){
			p.getInventory().setHelmet(null);
			p.closeInventory();
		}
		}
	  }
	}
	
}
