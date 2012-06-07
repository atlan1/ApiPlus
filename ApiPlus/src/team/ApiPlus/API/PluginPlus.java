package team.ApiPlus.API;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.material.CustomItem;

public abstract class PluginPlus extends JavaPlugin{

	public String pre;
	public Logger log = Bukkit.getLogger();
	public boolean warnings = true;
	public boolean debug = false;
	
	public void warn(String s){
		if(warnings)
			log.warning(pre+s);
	}
	
	public void debug(String s){
		if(debug)
			log.warning(pre+"[Debug] "+s);
	}
	
	public void info(String s){
		log.info(pre+s);
	}
	
	public abstract void addItem(CustomItem ci);
	public abstract List<Material> getTransparentMaterials();
	
}
