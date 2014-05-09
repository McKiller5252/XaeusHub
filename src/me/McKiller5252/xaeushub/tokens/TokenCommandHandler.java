package me.McKiller5252.xaeushub.tokens;

import me.McKiller5252.xaeushub.XaeusHub;
import net.minecraft.util.org.apache.commons.lang3.math.NumberUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TokenCommandHandler implements CommandExecutor {
	
	public ChatColor sColor = ChatColor.GRAY;
	public ChatColor pColor = ChatColor.GOLD;
	public ChatColor dColor = ChatColor.YELLOW;
	
	public String pre = ChatColor.YELLOW + "[" + ChatColor.GOLD + "XaeusNetwork" + ChatColor.YELLOW + "] ";
	public String pre1 = sColor + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.YELLOW + "[" + ChatColor.GOLD + "XaeusNetwork" + ChatColor.YELLOW + "]" + sColor + "" + ChatColor.STRIKETHROUGH + "--------------------";
	public String pre2 = sColor + "" + ChatColor.STRIKETHROUGH + "-----------------------------------------------------";
	
	
	@SuppressWarnings("deprecation")
	@Override
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 if (sender instanceof Player) {
	            if (cmd.getName().equalsIgnoreCase("tokens")) {
	                Player player = (Player) sender;
	                if (player.hasPermission("xaeushub.tokens.command")) {
	                    if (args.length == 0) {
	                    	player.sendMessage(pre1);
	                        player.sendMessage(pColor + "/tokens - " + dColor + "Displays this screen");
	                        player.sendMessage(pColor + "/tokens add [player] [amount] - " + dColor + "Give tokens");
	                        player.sendMessage(pColor + "/tokens set [player] [amount] - " + dColor + "Sets specific amount of tokens");
	                        player.sendMessage(pColor + "/tokens remove [player] [amount] - " + dColor + "Removes tokens");
	                        player.sendMessage(pre2);
	                    } else if (args.length == 3) {
	                        if (args[0].equalsIgnoreCase("add")) {
	                            Player target = Bukkit.getPlayerExact(args[1]);
	                            if (target == null) {
	                                player.sendMessage(pre + "§4" + args[1] + " §7is offline!");
	                            } else {
	                                if (NumberUtils.isNumber(args[2])) {
	                                    int amount = Integer.parseInt(args[2]);
	                                    TokenApi.getManager().addTokens(target.getName(), amount);
	                                    XaeusHub.getBoard().updatescoreboardforeveryone();
	                                    player.sendMessage(pre + "§7You have added §2" + args[2] + "§7 tokens to §2" + args[1] + "'s §7account.");
	                                } else {
	                                    player.sendMessage(pre + "§4" + args[2] + " §7is not a number!");
	                                }
	                            }
	                        }
	                    if(args[0].equalsIgnoreCase("remove")){
	                    		 Player target1 = Bukkit.getPlayerExact(args[1]);
		                            if (target1 == null) {
		                                player.sendMessage(pre + "§4" + args[1] + " §7is offline!");
		                            } else {
		                                if (NumberUtils.isNumber(args[2])) {
		                                    int amount = Integer.parseInt(args[2]);
		                                    TokenApi.getManager().removeTokens(target1.getName(), amount);
		                                    XaeusHub.getBoard().updatescoreboardforeveryone();
		                                    player.sendMessage(pre + "§7You have removed §2" + args[2] + "§7 tokens from §2" + args[1] + "'s §7account.");
		                                } else {
		                                    player.sendMessage(pre + "§4" + args[2] + " §7is not a number!");
		                                }
		                            }
		                       }
	                    if(args[0].equalsIgnoreCase("set")){
	                    	Player target2 = Bukkit.getPlayerExact(args[1]);
	                    	if(NumberUtils.isNumber(args[2])){
	                    		int amount = Integer.parseInt(args[2]);
	                    		TokenApi.getManager().setTokens(target2.getName(), amount);
	                    		 XaeusHub.getBoard().updatescoreboardforeveryone();
                                 player.sendMessage(pre + "§7You have set §2" + args[2] + "§7 tokens to §2" + args[1] + "'s §7account.");
                             } else {
                                 player.sendMessage(pre + "§4" + args[2] + " §7is not a number!");
	                    	}
	                    	
	                    }
	                } 
	            }
	             else {
	                  player.sendMessage(pre + "You do not have permission to use this command!");
	                }
	          }
		 }
		 return false;
	 }
}
