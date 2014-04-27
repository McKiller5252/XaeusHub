package me.McKiller5252.xaeushub.tokens;

import java.util.HashMap;

import org.bukkit.scheduler.BukkitRunnable;

import me.McKiller5252.xaeushub.XaeusHub;

public class TokenApi {
	
	private static TokenApi tokens = new TokenApi();
	
	
	public static TokenApi getManager(){
		return tokens;
	}
	
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	 
	 
	 public int getTokens(String name){
		if (map.containsKey(name)) {
            return map.get(name);
        } else {
            return 0;
        }
		
	 }
	 
	 public void addTokens(String name, int amount_to_add) {
	        if (map.containsKey(name)) {
	            map.put(name, map.get(name) + amount_to_add);
	            XaeusHub.getPlugin().getConfig().set(name, getTokens(name));
	        } else {
	            map.put(name, amount_to_add);
	            XaeusHub.getPlugin().getConfig().set(name, getTokens(name));
	        }
	      
	 }
	 
	 public void setTokens(String name, int amount) {
		 map.put(name,  amount);
		 XaeusHub.getPlugin().getConfig().set(name, getTokens(name));
		 
	} 
	 
	 public boolean hasTokens(String name) {
        return map.containsKey(name);
    }

    public void removeTokens(String name, int cost) {
        map.put(name, getTokens(name) - cost);
        XaeusHub.getPlugin().getConfig().set(name, getTokens(name));
        
    }
    
	 
	public void loadConfig() {
		for(String keys : XaeusHub.getPlugin().getConfig().getKeys(false)){
            int amount = XaeusHub.getPlugin().getConfig().getInt(keys);
            map.put(keys, amount);
        }
		System.out.print("[XaeusHub] Loaded Tokens Configuration File!");
	
	}

    public void startSaveTask(){
        new BukkitRunnable(){
            public void run(){
            	XaeusHub.getPlugin().saveConfig();
            	XaeusHub.getBoard().updatescoreboardforeveryone();
            }
        }.runTaskTimer(XaeusHub.getPlugin(), 20*5, 20);
    }
	



	

}
