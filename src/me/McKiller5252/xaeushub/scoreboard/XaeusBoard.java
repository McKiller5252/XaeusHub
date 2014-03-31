package me.McKiller5252.xaeushub.scoreboard;

import me.McKiller5252.xaeushub.XaeusHub;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
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

	public XaeusBoard(XaeusHub i) {
		plugin = i;
	}	

	public void addPlayer(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = board.registerNewObjective("XaeusHub", "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(prefix);
		
		int a = Bukkit.getServer().getOnlinePlayers().length;
		
		o.getScore(Bukkit.getOfflinePlayer(g + b.toString() + "Online:")).setScore(5);
		o.getScore(Bukkit.getOfflinePlayer(y + "" + a)).setScore(4);
		o.getScore(Bukkit.getOfflinePlayer("")).setScore(3);
		o.getScore(Bukkit.getOfflinePlayer(g + b.toString() + "Website:")).setScore(2);
		o.getScore(Bukkit.getOfflinePlayer(y + "xaeus.net")).setScore(1);
		o.getScore(Bukkit.getOfflinePlayer(r.toString() + line.toString() + "------------")).setScore(0);
		p.setScoreboard(board);
	}

	public void updatescoreboardforeveryone() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for(Player online : plugin.getServer().getOnlinePlayers()) {
					addPlayer(online);
				}
			}
		}.runTaskLater(plugin, 10);
		
	}

}