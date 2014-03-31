package me.McKiller5252.xaeushub;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class XaeusHub extends JavaPlugin implements Listener{
	
	public XaeusHub plugin;
	private XaeusBoard sb;
	
	public void onEnable(){
		plugin = this;
		sb = new XaeusBoard(this);
		registerdEvents();
		
	}
	
	private void registerdEvents() {
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		getServer().getPluginManager().registerEvents(this, this);
	}

	public XaeusBoard getXaeusBoard(){
		return sb;
	}

}
