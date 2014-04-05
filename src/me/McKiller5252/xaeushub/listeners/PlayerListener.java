package me.McKiller5252.xaeushub.listeners;

import java.util.Arrays;

import me.McKiller5252.xaeushub.XaeusHub;
import me.McKiller5252.xaeushub.tokens.Tokens;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class PlayerListener implements Listener {
	
	@EventHandler 
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		XaeusHub.getBar().showBarChanging(e.getPlayer());
		XaeusHub.getBoard().updatescoreboardforeveryone();
		int reward = 5;
		Tokens.getManager().addTokens(p.getName(), reward);
		hatshopItem(p);
	}
	@EventHandler 
	public void onPlayerQuit(PlayerQuitEvent e) {
		 XaeusHub.getBoard().updatescoreboardforeveryone();
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e){
	   XaeusHub.getBoard().updatescoreboardforeveryone();
	}
	@EventHandler
	public void onRainStart(WeatherChangeEvent event) {
		event.setCancelled(true);
	}

	
	private void hatshopItem(Player p) {
		if (!p.getInventory().contains(Material.GHAST_TEAR)){
			 ItemStack spawnItem = new ItemStack(Material.GHAST_TEAR);
			 ItemMeta im =  spawnItem.getItemMeta();
			 im.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Xaeus Hat Shop (Right Click)");
			 im.setLore(Arrays.asList(ChatColor.AQUA + "Right click to open Hat Shop"));
			 spawnItem.setItemMeta(im);
			 p.getInventory().setItem(6, spawnItem);
	    }
		
	}

}
