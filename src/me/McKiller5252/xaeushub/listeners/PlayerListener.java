package me.McKiller5252.xaeushub.listeners;

import me.McKiller5252.xaeushub.XaeusHub;
import me.McKiller5252.xaeushub.tokens.Tokens;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;


public class PlayerListener implements Listener {
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		XaeusHub.getBar().showBarChanging(e.getPlayer());
		XaeusHub.getBoard().updatescoreboardforeveryone();
		int reward = 1;
		Tokens.getManager().addTokens(p.getName(), reward);
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		 XaeusHub.getBoard().updatescoreboardforeveryone();
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent e){
	   XaeusHub.getBoard().updatescoreboardforeveryone();
	}
	@EventHandler (priority = EventPriority.NORMAL)
	public void onRainStart(WeatherChangeEvent event) {
		event.setCancelled(true);
	}

}
