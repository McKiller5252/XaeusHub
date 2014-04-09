package me.McKiller5252.xaeushub;

import me.McKiller5252.xaeushub.bar.XaeusBar;
import me.McKiller5252.xaeushub.config.ConfigManager;
import me.McKiller5252.xaeushub.listeners.PlayerListener;
import me.McKiller5252.xaeushub.scoreboard.XaeusBoard;
import me.McKiller5252.xaeushub.shop.XaeusShop;
import me.McKiller5252.xaeushub.tokens.TokenCommandHandler;
import me.McKiller5252.xaeushub.tokens.Tokens;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class XaeusHub extends JavaPlugin implements Listener{
	
	public static XaeusHub plugin;
	public static XaeusBoard sb;
	public static XaeusBar bar;
	private XaeusShop xsg;
	
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
		try{
			ConfigManager.load(this, "Hats.yml");
			getServer().getPluginManager().registerEvents(new PlayerListener(), this);
			getServer().getPluginManager().registerEvents(this, this);
			getCommand("tokens").setExecutor(new TokenCommandHandler());
			Tokens.getManager().loadConfig();
			startAutoSaveTask();
			
			sb = new XaeusBoard(this);
			bar = new XaeusBar(this);
			xsg = new XaeusShop(this);
		} 
		catch (Exception e){
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK | e.getAction() == Action.RIGHT_CLICK_AIR){
        if (p.getItemInHand() != null) {
            ItemStack item = p.getItemInHand();
            if (item.getType() == Material.GHAST_TEAR) { 
            	xsg.show(e.getPlayer()); 
                }
		}
       }
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
