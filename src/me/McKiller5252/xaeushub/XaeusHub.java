package me.McKiller5252.xaeushub;

import me.McKiller5252.xaeushub.bar.XaeusBar;
import me.McKiller5252.xaeushub.listeners.PlayerListener;
import me.McKiller5252.xaeushub.scoreboard.XaeusBoard;
import me.McKiller5252.xaeushub.tokens.TokenCommandHandler;
import me.McKiller5252.xaeushub.tokens.Tokens;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class XaeusHub extends JavaPlugin implements Listener{
	
	private static XaeusHub plugin;
	
	public static XaeusBoard sb;
	public static XaeusBar bar;
	
	public static XaeusHub getPlugin(){
		return plugin;
	}
	public static XaeusBoard getBoard(){
		return sb;
	}
	public static XaeusBar getBar(){
		return bar;
	}
	
	public void onEnable(){
		this.saveConfig();
		plugin = this;
	    getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(this, this);
		this.getCommand("tokens").setExecutor(new TokenCommandHandler());
		Tokens.getManager().loadConfig();
		this.startAutoSaveTask();
		
		sb = new XaeusBoard(this);
		bar = new XaeusBar(this);
	}
	

	public void startAutoSaveTask(){
        new BukkitRunnable(){
            public void run(){
                System.out.println("[XaeusHub] Saving Tokens Config...");
                saveConfig();
                XaeusHub.getBoard().updatescoreboardforeveryone();
                System.out.println("[XaeusHub] Saving Complete!");
            }
        }.runTaskTimer(XaeusHub.getPlugin(), 20*10, 20*60*5);
    }
	
	public void onDisable(){
        this.saveConfig();
    }
}
