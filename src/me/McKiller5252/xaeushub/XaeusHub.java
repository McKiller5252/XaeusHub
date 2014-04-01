package me.McKiller5252.xaeushub;

import me.McKiller5252.xaeushub.bar.XaeusBar;
import me.McKiller5252.xaeushub.listeners.PlayerListener;
import me.McKiller5252.xaeushub.scoreboard.XaeusBoard;
import me.McKiller5252.xaeushub.tokens.Tokens;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class XaeusHub extends JavaPlugin implements Listener{
	
	private static XaeusHub plugin;
	
	final public XaeusBoard sb = new XaeusBoard(this);
	final public XaeusBar bar = new XaeusBar(this);
	
	public static XaeusHub getPlugin(){
		return plugin;
	}
	
	public void onEnable(){
		this.saveConfig();
		plugin = this;
	    getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		getServer().getPluginManager().registerEvents(this, this);
		Tokens.getManager().loadConfig();
		this.startAutoSaveTask();
	}
	

	public void startAutoSaveTask(){
        new BukkitRunnable(){
            public void run(){
                System.out.println("[XaeusHub] Saving Tokens Config...");
                reloadConfig();
                saveConfig();
                System.out.println("[XaeusHub] Saving Complete!");
            }
        }.runTaskTimer(XaeusHub.getPlugin(), 20*10, 20*60*5);
    }
	
	public void onDisable(){
        this.saveConfig();
    }
}
