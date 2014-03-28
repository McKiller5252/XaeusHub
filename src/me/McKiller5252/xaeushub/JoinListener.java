package me.McKiller5252.xaeushub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {
	
	XaeusHub plugin;
	
	public ChatColor y = ChatColor.YELLOW;
	
	public JoinListener(XaeusHub i) {
		plugin = i;
		
		plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					showBarChanging(player);
				}
			}
		});
		
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		showBarChanging(p);
		
	   plugin.getXaeusBoard().addPlayer(p);
	}
	
	public void showBarChanging(final Player p){
		
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
			public void run()
			{
			    Random random = new Random();
				List<String> list = new ArrayList<String>();
				list.add(y + "Welcome to the Xaeus Network!");
				list.add(y + "Want to support the server? - store.xaeus.net");
				list.add(y + "Want to check out our website? - www.xaeus.net");
				
				 String message = (String)list.get(random.nextInt(list.size()));
			     BarAPI.setMessage(p, message);
				}
			}, 0L, 100L);
		}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.getXaeusBoard().removePlayer(event.getPlayer());
	}

}
