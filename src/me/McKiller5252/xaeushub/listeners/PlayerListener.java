package me.McKiller5252.xaeushub.listeners;

import java.util.ArrayList;
import java.util.Arrays;

import me.McKiller5252.xaeushub.XaeusHub;
import me.McKiller5252.xaeushub.tokens.Tokens;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;


public class PlayerListener implements Listener {
	
	ArrayList<Player> cooldown = new ArrayList<Player>();
	
	int reward = 5;
	
	@EventHandler 
	public void onJoin(PlayerJoinEvent e){
		final Player p = e.getPlayer();
		XaeusHub.getBar().showBarChanging(e.getPlayer());
		XaeusHub.getBoard().updatescoreboardforeveryone();
		hatshopItem(p);
		
		if(cooldown.contains(p)){
		Tokens.getManager().addTokens(p.getName(), reward);
		cooldown.remove(p);
		new BukkitRunnable(){
			public void run() {
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.UNDERLINE + "You just got " + ChatColor.GOLD + reward + ChatColor.GREEN + " tokens for loging on today, Next reward in 24 Hours!");
			}	
		}.runTaskLater(XaeusHub.getPlugin(), 20);
		
		} else {
			new BukkitRunnable(){
				public void run() {
					p.sendMessage(ChatColor.GREEN + "" + ChatColor.UNDERLINE + "Already got your reward today, Next reward in 24 Hours!");
					}
				}.runTaskLater(XaeusHub.getPlugin(), 20);
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(XaeusHub.getPlugin(), new Runnable() {
            public void run() {
                    cooldown.add(p);
            }}, 86400);
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
