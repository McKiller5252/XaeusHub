package me.McKiller5252.xaeushub.shop;

import java.util.ArrayList;
import java.util.List;

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

public class XaeusShop implements Listener{
	
	private Inventory hat;
	
	private ItemStack a, b, c, d, e, f, g, h, i, j;
	private ItemStack z;
	
	public Player p;
	
	public ChatColor gre = ChatColor.GOLD;
	public ChatColor lock = ChatColor.RED;
	public ChatColor unlock = ChatColor.GREEN;
	
	public String pre = ChatColor.YELLOW + "[" + ChatColor.GOLD + "XaeusNetwork" + ChatColor.YELLOW + "] ";
	
	 int Miner = 100;
	 int Cob = 1000;
	 int Nature = 150;
	 int Space = 250;
	 int Beach = 350;
	 int Explosion = 750;
	 int Treasure = 1000;
	 int Handy = 550;
	 int Pumpkin = 750;
	 int Snow = 850;
	 
	public XaeusShop(Plugin m){
		hat = Bukkit.getServer().createInventory(null, 27, ChatColor.YELLOW + "Xaeus Hat Shop");
		
		//All Hats
		a = createItem(Material.DIAMOND_ORE, gre + "Miner's Hat", Miner);
		b = createItem(Material.WEB, gre + "Spiderman Hat", Cob);
		c = createItem(Material.LEAVES, gre + "Nature Hat", Nature);
		d = createItem(Material.GLASS, gre + "Spaceman Hat", Space);
		e = createItem(Material.SAND, gre + "Beach Bum Hat", Beach);
		f = createItem(Material.TNT, gre + "Griefer Hat", Explosion);
		g = createItem(Material.CHEST, gre + "Treasure Hat", Treasure);
		h = createItem(Material.WORKBENCH, gre + "Working Man Hat", Handy);
		i = createItem(Material.PUMPKIN, gre + "Halloween Mask", Pumpkin);
		j = createItem(Material.SNOW_BLOCK, gre + "Christmas Hat", Snow);
		
		hat.setItem(0, a);
		hat.setItem(1, b);
		hat.setItem(2, c);
		hat.setItem(3, d);
		hat.setItem(4, e);
		hat.setItem(5, f);
		hat.setItem(6, g);
		hat.setItem(7, h);
		hat.setItem(8, i);
		hat.setItem(9, j);
		
		//Clear Hat
		z = createRemove(Material.GOLD_HELMET, ChatColor.GOLD + "Click to remove your Hat!");
		hat.setItem(22, z);
		
		
	   Bukkit.getServer().getPluginManager().registerEvents(this, m);
	}
	
	private ItemStack createItem(Material dc, String name, int cost){
		ItemStack i = new ItemStack(dc);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		List<String> lore = new ArrayList<String>();
		lore.add("§7Cost: §6" + cost);
		lore.add(lock + "Click To Purchase");
		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}
	
	private ItemStack createRemove(Material dc, String name){
		ItemStack i = new ItemStack(dc);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}
	

	public void show(Player p) {
		p.openInventory(hat);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void click(InventoryClickEvent ev){
		Player p = (Player) ev.getWhoClicked();
		//int money = XaeusHub.getPlugin().getConfig().getInt(p.getName(), Tokens.getManager().getTokens(p.getName()));
		
		if (ev.getCurrentItem() != null && !ev.getCurrentItem().getType().equals(Material.AIR)){
			
		if (ev.getInventory().getSize() == 27) {
			ev.setCancelled(true);
			
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Miner's Hat")){
			p.getInventory().setHelmet(a);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Spiderman Hat")){
			p.getInventory().setHelmet(b);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Nature Hat")){
			p.getInventory().setHelmet(c);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Spaceman Hat")){
			p.getInventory().setHelmet(d);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Beach Bum Hat")){
			p.getInventory().setHelmet(e);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Griefer Hat")){
			p.getInventory().setHelmet(f);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Treasure Hat")){
			p.getInventory().setHelmet(g);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Work's Man Hat")){
			p.getInventory().setHelmet(h);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Halloween Mask")){
			p.getInventory().setHelmet(i);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Christmas Hat")){
			p.getInventory().setHelmet(j);
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Click to remove your Hat!")){
			if(p.getInventory().getHelmet() != null){
				p.getInventory().setHelmet(null);
			}else{
			p.sendMessage(pre + lock + "You don't have a hat on to remove!");
			}
			p.closeInventory();
		}
		}
	  }
	}
}
