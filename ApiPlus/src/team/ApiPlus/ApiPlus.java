package team.ApiPlus;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class ApiPlus extends JavaPlugin{
	
	public static final String PRE = "[API+] ";
	public static ApiPlus plugin;
	public static boolean useFurnaceAPI = false;
	public static WorldGuardPlugin wg;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map<String, Class<? extends GenericCustomItem>> types = new HashMap();
	
	Logger log = Bukkit.getLogger();
	
	@Override
	public void onDisable(){
		log.info(PRE+"is now disabled!");
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		hook();
		log.info(PRE+"is now enabled!");
	}
	
	private void hook(){
		Plugin spout = getServer().getPluginManager().getPlugin("Spout");
		Plugin furnaceAPI = getServer().getPluginManager().getPlugin("FurnaceAPI");
		Plugin worldguard = getServer().getPluginManager().getPlugin("WorldGuard");
		
		if(spout != null) {
		    log.log(Level.INFO, PRE+" Plugged into Spout!");
		}else{
			//disable this, because it would do nothing without spout
			log.log(Level.INFO, PRE+" disableing because Spout is missing!");
			this.setEnabled(false);
		}
		if(furnaceAPI != null) {
			useFurnaceAPI = true;	
			log.log(Level.INFO, PRE+" Plugged into FurnaceAPI!");
		}
		if(worldguard != null) {
			wg = (WorldGuardPlugin) worldguard;
			log.log(Level.INFO, PRE+" Plugged into WorldGuard!");
		}
	}
	
	public static Map<String, Class<? extends GenericCustomItem>> getTypes(){
		return types;
	}
	
}
