package me.McKiller5252.xaeushub.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.McKiller5252.xaeushub.XaeusHub;
import me.McKiller5252.xaeushub.config.ConfigManager;
import me.McKiller5252.xaeushub.tokens.TokenApi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class XaeusShop implements Listener{

	private Inventory hat;

	//Hats
	private ItemStack a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q;
	//Clear Hat
	private ItemStack z;

	//Colors
	public ChatColor gre = ChatColor.GOLD;
	public ChatColor lock = ChatColor.RED;
	public ChatColor unlock = ChatColor.GREEN;
	public ChatColor bold = ChatColor.BOLD;
	public ChatColor under = ChatColor.UNDERLINE;
	
	//Prefix
	public String pre = ChatColor.YELLOW + "[" + ChatColor.GOLD + "XaeusNetwork" + ChatColor.YELLOW + "] ";
	
    Random rand = new Random();
    
	FileConfiguration config = ConfigManager.get("hats.yml");
	
	//TaskId
	final HashMap<String, Integer> taskID1 = new HashMap<String, Integer>();
	final HashMap<String, Integer> taskID2 = new HashMap<String, Integer>();
	final HashMap<String, Integer> taskID3 = new HashMap<String, Integer>();

	//Hat Token Values
	 int Nature = 150;
	 int Treasure = 250;
	 int Space = 250;
	 int Beach = 350; 
	 int Cob = 500;
	 int Handy = 550;
	 int Explosion = 750;
	 int Pumpkin = 750;
	 int Snow = 850;
	 int Skater = 2500;
	 int horse = 500;
	 int libar= 250;
	 int mush = 10000;
	 int Wooly = 3000;
	 int Dj = 4000;
	 int Miner = 5000;
	 int Party = 10000;
	 
	public XaeusShop(Plugin pl){
		hat = Bukkit.getServer().createInventory(null, 27, ChatColor.MAGIC + "h " +  ChatColor.DARK_AQUA + under.toString() + "Xaeus Hat Shop" + ChatColor.RESET + ChatColor.MAGIC + " h");

		//All Hats
		a = createItem(Material.DIAMOND_ORE, gre + "Miner's Hat", Miner);
		b = createItem(Material.WEB, gre + "Spiderman Hat", Cob);
		c = createItem(Material.LEAVES, gre + "Nature Hat", Nature);
		d = createItem(Material.GLASS, gre + "Spaceman Hat", Space);
		e = createItem(Material.SAND, gre + "Beach Bum Hat", Beach);
		f = createItem(Material.TNT, gre + "Griefer Hat", Explosion);
		g = createItem(Material.CHEST, gre + "Treasure Hat", Treasure);
		h = createItem(Material.WORKBENCH, gre + "Work's Man Hat", Handy);
		i = createItem(Material.JACK_O_LANTERN, gre + "Halloween Mask", Pumpkin);
		j = createItem(Material.SNOW_BLOCK, gre + "Christmas Hat", Snow);
		k = createItem(Material.JUKEBOX, gre + "DJ Hat", Dj);
		l = createItem(Material.DIAMOND_HELMET, gre + "Party Hat", Party);
		m = createItem(Material.WOOL, gre + "Disco Hat", Wooly);
		n = createItem(Material.PACKED_ICE, gre + "Skater Hat", Skater);
		o = createItem(Material.HAY_BLOCK, gre + "Horsey Hat", horse);
		p = createItem(Material.BOOKSHELF, gre + "Librarian's hat", libar);
		q = createItem(Material.RED_MUSHROOM, gre + "Soup Hat", mush);

		hat.setItem(0, a);
		hat.setItem(1, b);
		hat.setItem(2, c);
		hat.setItem(3, d);
		hat.setItem(4, e);
		hat.setItem(5, f);
		hat.setItem(6, g);
		hat.setItem(7, h);
		hat.setItem(8, i);
		hat.setItem(9, j);
		hat.setItem(10, k);
		hat.setItem(26, l);
		hat.setItem(11, m);
		hat.setItem(12, n);
		hat.setItem(13, o);
		hat.setItem(14, p);
		hat.setItem(15, q);

		//Clear Hat
		z = createRemove(Material.GOLD_HELMET, ChatColor.GOLD + "Click to remove your Hat!");
		hat.setItem(22, z);


	   Bukkit.getServer().getPluginManager().registerEvents(this, pl);
	}

	private ItemStack createItem(Material dc, String name, int cost){	
		List<String> lore = new ArrayList<String>();
		ItemStack i = new ItemStack(dc);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		lore.add(ChatColor.GRAY + "Tokens: " + ChatColor.YELLOW + cost);
		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}

	private ItemStack createRemove(Material dc, String name){
		ItemStack i = new ItemStack(dc);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}
	public void show(Player p) {
		p.openInventory(hat);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void click(InventoryClickEvent ev){
		if(ev.getWhoClicked() instanceof Player){
		final Player p = (Player) ev.getWhoClicked();
		int money = XaeusHub.getPlugin().getConfig().getInt(p.getName(), TokenApi.getManager().getTokens(p.getName()));

		if (ev.getCurrentItem() != null && !ev.getCurrentItem().getType().equals(Material.AIR)){

		if (ev.getInventory().getSize() == 27) {
			ev.setCancelled(true);

		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Miner's Hat")){
			String path = "Hats.Players." + p.getName() + ".Miners Hat";
			if(!config.contains(path)){
				if(money >= Miner){
					ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
					p.sendMessage(pre + unlock + "You successfully bought the Miner's Hat");
					
					TokenApi.getManager().removeTokens(p.getName(), Miner);
					XaeusHub.getBoard().updatescoreboardforeveryone();
					
					config.set(path, "Bought");
					ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				taskID1.put(p.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(XaeusHub.getPlugin(), new Runnable() {
						int currentHelm = 0;
						Material[] helmMaterials = { Material.DIAMOND_ORE, Material.REDSTONE_ORE, Material.COAL_ORE,
								Material.EMERALD_ORE, Material.LAPIS_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.QUARTZ_ORE};
						public void run()
						{
							ItemStack helm = new ItemStack(this.helmMaterials[this.currentHelm], 1);
							p.getInventory().setHelmet(helm);
							this.currentHelm = ((this.currentHelm + 1) % this.helmMaterials.length);
						}
				     }, 1L, 5L));
				}else{
					p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
					}
				}else{
					p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
					taskID1.put(p.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(XaeusHub.getPlugin(), new Runnable() {
						int currentHelm = 0;
						Material[] helmMaterials = { Material.DIAMOND_ORE, Material.REDSTONE_ORE, Material.COAL_ORE,
								Material.EMERALD_ORE, Material.LAPIS_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.QUARTZ_ORE};
						public void run()
						{
							ItemStack helm = new ItemStack(this.helmMaterials[this.currentHelm], 1);
							p.getInventory().setHelmet(helm);
							this.currentHelm = ((this.currentHelm + 1) % this.helmMaterials.length);
						}
					}, 1L, 5L));
				}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Skater Hat")){
			String path = "Hats.Players." + p.getName() + ".Skater Hat";
			if(!config.contains(path)){
			if(money >= Skater){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Skater Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Skater);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
			
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.PACKED_ICE, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.PACKED_ICE, 1));
				}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Horsey Hat")){
			String path = "Hats.Players." + p.getName() + ".Horsey Hat";
			if(!config.contains(path)){
			if(money >= horse){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Horsey Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), horse);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
			
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.HAY_BLOCK, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.HAY_BLOCK, 1));
				}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Librarian's hat")){
			String path = "Hats.Players." + p.getName() + ".Librarians hat";
			if(!config.contains(path)){
			if(money >= libar){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Librarian's hat");
				
				TokenApi.getManager().removeTokens(p.getName(), libar);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
			
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.BOOKSHELF, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.BOOKSHELF, 1));
				}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Soup Hat")){
			String path = "Hats.Players." + p.getName() + ".Soup Hat";
			if(!config.contains(path)){
			if(money >= mush){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Soup Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), mush);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
			
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.RED_MUSHROOM, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.RED_MUSHROOM, 1));
				}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Spiderman Hat")){
			String path = "Hats.Players." + p.getName() + ".Spiderman Hat";
			if(!config.contains(path)){
			if(money >= Cob){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Spiderman Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Cob);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
			
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.WEB, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.WEB, 1));
				}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Nature Hat")){
			String path = "Hats.Players." + p.getName() + ".Nature Hat";
			if(!config.contains(path)){
			if(money >= Nature){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Nature Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Nature);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.LEAVES, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
		    }else{
		    	p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.LEAVES, 1));
			}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Spaceman Hat")){
			String path = "Hats.Players." + p.getName() + ".Spaceman Hat";
			if(!config.contains(path)){
			if(money >= Space){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Spaceman Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Space);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
				
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.GLASS, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.GLASS, 1));
			}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Beach Bum Hat")){
			String path = "Hats.Players." + p.getName() + ".Beach Bum Hat";
			if(!config.contains(path)){
			if(money >= Beach){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Beach Bum Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Beach);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
				
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.SAND, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.SAND, 1));
			}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Griefer Hat")){
			String path = "Hats.Players." + p.getName() + ".Griefer Hat";
			if(!config.contains(path)){
			if(money >= Explosion){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Griefer Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Explosion);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
				
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.TNT, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.TNT, 1));
			}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Treasure Hat")){
			String path = "Hats.Players." + p.getName() + ".Treasure Hat";
			if(!config.contains(path)){
			if(money >= Treasure){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Treasure Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Treasure);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.CHEST, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.CHEST, 1));
			}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Work's Man Hat")){
			String path = "Hats.Players." + p.getName() + ".Works Man";
			if(!config.contains(path)){
			if(money >= Handy){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Work's Man");
				
				TokenApi.getManager().removeTokens(p.getName(), Handy);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
				
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.WORKBENCH, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.WORKBENCH, 1));
			}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Halloween Mask")){
			String path = "Hats.Players." + p.getName() + ".Halloween Mask";
			if(!config.contains(path)){
			if(money >= Pumpkin){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Halloween Mask");
				
				TokenApi.getManager().removeTokens(p.getName(), Pumpkin);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
				
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.JACK_O_LANTERN, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				p.getInventory().setHelmet(new ItemStack(Material.JACK_O_LANTERN, 1));
			}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Christmas Hat")){
			String path = "Hats.Players." + p.getName() + ".Christmas Hat";
			if(!config.contains(path)){
			if(money >= Snow){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Christmas Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Snow);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
				
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.SNOW_BLOCK, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.getInventory().setHelmet(new ItemStack(Material.SNOW_BLOCK, 1));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
			}
			p.closeInventory();
		}if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("DJ Hat")){
			String path = "Hats.Players." + p.getName() + ".DJ Hat";
			if(!config.contains(path)){
			if(money >= Dj){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the DJ Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Dj);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
			
				
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				p.getInventory().setHelmet(new ItemStack(Material.JUKEBOX, 1));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.getInventory().setHelmet(new ItemStack(Material.JUKEBOX, 1));
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
			}
			p.closeInventory();
		}if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Party Hat")){
			String path = "Hats.Players." + p.getName() + ".Party Hat";
			if(!config.contains(path)){
			if(money >= Party){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Party Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Party);
				XaeusHub.getBoard().updatescoreboardforeveryone();
				
				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
			
				
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				taskID2.put(p.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(XaeusHub.getPlugin(), new Runnable() {
					int currentHelm = 0;
					Material[] helmMaterials = { Material.STONE, Material.GRASS, Material.DIRT, Material.COBBLESTONE, 
							Material.BEDROCK, Material.SAND, Material.GRAVEL, Material.GOLD_ORE, 
							Material.IRON_ORE, Material.COAL_ORE, Material.WOOD, Material.LEAVES, Material.SPONGE, 
							Material.GLASS, Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.DISPENSER, Material.SANDSTONE, Material.NOTE_BLOCK, 
							Material.PISTON_STICKY_BASE, Material.PISTON_BASE, Material.WOOL, Material.GOLD_BLOCK,Material.QUARTZ_ORE, 
							Material.IRON_BLOCK, Material.DOUBLE_STEP, Material.BRICK, Material.TNT, Material.BOOKSHELF, 
							Material.MOSSY_COBBLESTONE, Material.OBSIDIAN, Material.MOB_SPAWNER, Material.CHEST, Material.DIAMOND_ORE, 
							Material.WORKBENCH, Material.SOIL, Material.FURNACE, Material.REDSTONE_ORE, 
						    Material.SNOW_BLOCK, Material.ICE, Material.SNOW_BLOCK, Material.CACTUS, Material.CLAY, Material.JUKEBOX, 
						    Material.FENCE, Material.NETHERRACK, Material.SOUL_SAND,Material.GLOWSTONE, Material.PORTAL, Material.JACK_O_LANTERN, Material.CAKE_BLOCK, 
						    Material.SMOOTH_BRICK,Material.MELON_BLOCK, Material.MYCEL, Material.NETHER_BRICK, Material.NETHER_FENCE, Material.ENCHANTMENT_TABLE, 
							Material.ENDER_PORTAL_FRAME, Material.REDSTONE_LAMP_OFF, 
							Material.REDSTONE_LAMP_ON, Material.EMERALD_ORE, Material.ENDER_CHEST, Material.EMERALD_BLOCK, 
							Material.COMMAND, Material.BEACON, Material.COBBLE_WALL };
					public void run(){
						ItemStack helm = new ItemStack(this.helmMaterials[this.currentHelm], 1);
						p.getInventory().setHelmet(helm);
						this.currentHelm = ((this.currentHelm + 1) % this.helmMaterials.length);
					}
				}, 1L, 5L));
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				taskID2.put(p.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(XaeusHub.getPlugin(), new Runnable() {
					int currentHelm = 0;
					Material[] helmMaterials = { Material.STONE, Material.GRASS, Material.DIRT, Material.COBBLESTONE, 
							Material.BEDROCK, Material.SAND, Material.GRAVEL, Material.GOLD_ORE, 
							Material.IRON_ORE, Material.COAL_ORE, Material.WOOD, Material.LEAVES, Material.SPONGE, 
							Material.GLASS, Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.DISPENSER, Material.SANDSTONE, Material.NOTE_BLOCK, 
							Material.PISTON_STICKY_BASE, Material.PISTON_BASE, Material.WOOL, Material.GOLD_BLOCK, 
							Material.IRON_BLOCK, Material.DOUBLE_STEP, Material.BRICK, Material.TNT, Material.BOOKSHELF, Material.QUARTZ_ORE,
							Material.MOSSY_COBBLESTONE, Material.OBSIDIAN, Material.MOB_SPAWNER, Material.CHEST, Material.DIAMOND_ORE, 
							Material.WORKBENCH, Material.SOIL, Material.FURNACE, Material.REDSTONE_ORE, 
						    Material.SNOW_BLOCK, Material.ICE, Material.SNOW_BLOCK, Material.CACTUS, Material.CLAY, Material.JUKEBOX, 
						    Material.FENCE, Material.NETHERRACK, Material.SOUL_SAND,Material.GLOWSTONE, Material.PORTAL, Material.JACK_O_LANTERN, Material.CAKE_BLOCK, 
						    Material.SMOOTH_BRICK,Material.MELON_BLOCK, Material.MYCEL, Material.NETHER_BRICK, Material.NETHER_FENCE, Material.ENCHANTMENT_TABLE, 
							Material.ENDER_PORTAL_FRAME, Material.REDSTONE_LAMP_OFF, 
							Material.REDSTONE_LAMP_ON, Material.EMERALD_ORE, Material.ENDER_CHEST, Material.EMERALD_BLOCK, 
							Material.COMMAND, Material.BEACON, Material.COBBLE_WALL };
					public void run(){
						ItemStack helm = new ItemStack(this.helmMaterials[this.currentHelm], 1);
						p.getInventory().setHelmet(helm);
						this.currentHelm = ((this.currentHelm + 1) % this.helmMaterials.length);
					}
				}, 1L, 5L));
			}
			p.closeInventory();
		}
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Disco Hat")){
			String path = "Hats.Players." + p.getName() + ".Disco Hat";
			if(!config.contains(path)){
			if(money >= Wooly){
				ConfigManager.reload(XaeusHub.getPlugin(), "hats.yml");
				p.sendMessage(pre + unlock + "You successfully bought the Disco Hat");
				
				TokenApi.getManager().removeTokens(p.getName(), Wooly);
				XaeusHub.getBoard().updatescoreboardforeveryone();

				config.set(path, "Bought");
				ConfigManager.save(XaeusHub.getPlugin(), "hats.yml");
				
	
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
				taskID3.put(p.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(XaeusHub.getPlugin(), new Runnable() {
					List<DyeColor> discoColours = Arrays.asList(DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW, DyeColor.GREEN, 
							DyeColor.BLUE, DyeColor.LIGHT_BLUE,DyeColor.PINK, DyeColor.MAGENTA, DyeColor.LIME, DyeColor.CYAN, 
							DyeColor.PURPLE, DyeColor.BLACK, DyeColor.WHITE);
					@SuppressWarnings("deprecation")
					public void run(){
						DyeColor dyeColour = discoColours.get(rand.nextInt(discoColours.size()));
						ItemStack helm = new ItemStack(Material.WOOL, 1, dyeColour.getWoolData());
						p.getInventory().setHelmet(helm);
					}
			}, 1L, 5L));
				
			}else{
				p.sendMessage(pre + lock + "You don't have enough tokens to buy this hat.");
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 50, 1);
			}
			}else{
				p.sendMessage(pre + unlock + "You already own this hat, " + gre + "Hat placed on head.");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 50, 5);
				taskID3.put(p.getName(), Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(XaeusHub.getPlugin(), new Runnable() {
					List<DyeColor> discoColours = Arrays.asList(DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW, DyeColor.GREEN, 
							DyeColor.BLUE, DyeColor.LIGHT_BLUE,DyeColor.PINK, DyeColor.MAGENTA, DyeColor.LIME, DyeColor.CYAN, 
							DyeColor.PURPLE, DyeColor.BLACK, DyeColor.WHITE);
					@SuppressWarnings("deprecation")
					public void run(){
						DyeColor dyeColour = discoColours.get(rand.nextInt(discoColours.size()));
						ItemStack helm = new ItemStack(Material.WOOL, 1, dyeColour.getWoolData());
						p.getInventory().setHelmet(helm);
					}
				}, 1L, 5L));
				
			}
			p.closeInventory();
		}
		
		if(ev.getCurrentItem().getItemMeta().getDisplayName().contains("Click to remove your Hat!")){
			if(p.getInventory().getArmorContents() != null){
				if(taskID1.containsKey(p.getName())){
				  Bukkit.getScheduler().cancelTask(taskID1.get(p.getName()));
				  taskID1.remove(p.getName());
				}if(taskID2.containsKey(p.getName())){
				  Bukkit.getScheduler().cancelTask(taskID2.get(p.getName()));
				  taskID2.remove(p.getName());
				}if(taskID3.containsKey(p.getName())){
				  Bukkit.getScheduler().cancelTask(taskID3.get(p.getName()));
				  taskID3.remove(p.getName());
				}
				p.getInventory().setArmorContents(null);
				p.sendMessage(pre + unlock + "You successfully removed a hat!");
			}else{
				p.sendMessage(pre + lock + "You don't have a hat on to remove!");
			}
			p.closeInventory();	
		 }
		}
	   }
	  }
	 }
}