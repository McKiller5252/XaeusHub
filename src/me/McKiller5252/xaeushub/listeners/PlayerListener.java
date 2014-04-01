package me.McKiller5252.xaeushub.listeners;

import me.McKiller5252.xaeushub.XaeusHub;
import me.McKiller5252.xaeushub.tokens.Tokens;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;


public class PlayerListener implements Listener {
	
	XaeusHub plugin;
	
	int reward = 1;
	int penalty = 5;
	
	public PlayerListener(XaeusHub i) {
		plugin = i;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		plugin.bar.showBarChanging(e.getPlayer());
		plugin.sb.updatescoreboardforeveryone();
		Tokens.getManager().addTokens(p.getName(), reward);
		XaeusHub.getPlugin().reloadConfig();
		XaeusHub.getPlugin().saveConfig();
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		plugin.sb.updatescoreboardforeveryone();
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent e){
		Player p = e.getPlayer();
		plugin.sb.updatescoreboardforeveryone();
		Tokens.getManager().removeTokens(p.getName(), penalty);
	}

}
