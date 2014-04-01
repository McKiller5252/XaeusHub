package me.McKiller5252.xaeushub.bar;

import java.util.*;

import org.bukkit.*;
import org.bukkit.entity.*;

import me.McKiller5252.xaeushub.XaeusHub;
import me.confuser.barapi.BarAPI;

public class XaeusBar {
	
	XaeusHub plugin;
	
	public ChatColor y = ChatColor.YELLOW;
	public ChatColor g = ChatColor.GOLD;

	public XaeusBar(XaeusHub i) {
		plugin = i;
	}
	
	public void showBarChanging(final Player p){
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
			public void run(){
				Random random = new Random();
				List<String> list = new ArrayList<String>();
				list.add(y + "Welcome " + g + p.getName() + y + " to the Xaeus Network!");
				list.add(y + "Want to support the server? - store.xaeus.net");
				list.add(y + "Check out our website! - www.xaeus.net");
				
				 String message = (String)list.get(random.nextInt(list.size()));
			     BarAPI.setMessage(p, message);
				}
			}, 0L, 100L);
		}

}
