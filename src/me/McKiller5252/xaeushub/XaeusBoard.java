package me.McKiller5252.xaeushub;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class XaeusBoard {
	
	XaeusHub plugin;

	public ChatColor gr = ChatColor.GREEN;
	public ChatColor y = ChatColor.YELLOW;
	public ChatColor g = ChatColor.GOLD;
	public ChatColor line = ChatColor.STRIKETHROUGH;
	public ChatColor r = ChatColor.RED;
	public ChatColor b = ChatColor.BOLD;
	public ChatColor c = ChatColor.DARK_AQUA;
	public ChatColor aq = ChatColor.LIGHT_PURPLE;
	public String prefix = r + "" + line + "---" + g + "XaeusNetwork" + r + "" + line + "---";
	
	private Objective o;
	
	private HashMap<String, Scoreboard> playerboards;

	public XaeusBoard(XaeusHub i) {
		plugin = i;
		
		playerboards = new HashMap<String, Scoreboard>();
		
		plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					addPlayer(player);
				}
			}
		});
	}	
	
	public void addPlayer(final Player player) {
		final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		o = board.registerNewObjective("hub", "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(prefix);
		
		int a = Bukkit.getOnlinePlayers().length;
		
		o.getScore(Bukkit.getOfflinePlayer(g + b.toString() + "Online:")).setScore(5);
		o.getScore(Bukkit.getOfflinePlayer(y + "" + a)).setScore(4);
		o.getScore(Bukkit.getOfflinePlayer("")).setScore(3);
		o.getScore(Bukkit.getOfflinePlayer(g + b.toString() + "Website:")).setScore(2);
		o.getScore(Bukkit.getOfflinePlayer(y + "xaeus.net")).setScore(1);
		o.getScore(Bukkit.getOfflinePlayer(r.toString() + line.toString() + "------------")).setScore(0);
		
		
		playerboards.put(player.getName(), board);
		plugin.getServer().getScheduler().runTask(plugin, new Runnable() {
			public void run() {
				player.setScoreboard(board);
				
			}
		});
		
	}
	
	public void removePlayer(Player player) {
		if(playerboards.containsKey(player.getName())) {
			playerboards.remove(player.getName());
			player.setScoreboard(plugin.getServer().getScoreboardManager().getNewScoreboard());
			
		}
	}

}
