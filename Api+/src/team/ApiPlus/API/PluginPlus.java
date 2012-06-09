package team.ApiPlus.API;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.Manager.ItemManager;

abstract public class PluginPlus extends JavaPlugin {
	
	public boolean addItem(String name, String texture, String baseType) {
		try {
			ItemManager.getInstance().buildItem(this, name, texture, baseType);
			return true;
		} catch (Exception e) {
			Bukkit.getLogger().log(Level.INFO, String.format("[%s][ERROR] %s %s", this.getName(), e.getMessage(), e.getCause()));
			return false;
		}
	}
}
