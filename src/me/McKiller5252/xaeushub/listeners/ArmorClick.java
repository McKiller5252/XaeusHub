package me.McKiller5252.xaeushub.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

public class ArmorClick implements Listener {
	
	public String pre = ChatColor.YELLOW + "[" + ChatColor.GOLD + "XaeusNetwork" + ChatColor.YELLOW + "] ";
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if(event.getSlotType().equals(SlotType.ARMOR)){
			event.setCancelled(true);
			p.sendMessage(pre + ChatColor.RED + "You don't have the right to remove your armor slot content, Use the remove tool in the Hatshop.");
			p.closeInventory();
		}
	}
}
