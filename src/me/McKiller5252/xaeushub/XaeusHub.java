package me.McKiller5252.xaeushub;

import me.McKiller5252.xaeushub.listeners.JoinListener;
import me.McKiller5252.xaeushub.scoreboard.XaeusBoard;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class XaeusHub extends JavaPlugin implements Listener{
	
	public XaeusHub plugin;
	
	final public XaeusBoard sb = new XaeusBoard(this);
	
	public void onEnable(){
		plugin = this;
		
		registerdEvents();
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){
				for(Player online : getServer().getOnlinePlayers()) { 
					sb.addPlayer(online); 
				}
			}
		},0, 3 * 20);
		
	}
	
	private void registerdEvents() {
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		getServer().getPluginManager().registerEvents(this, this);
	}

}
