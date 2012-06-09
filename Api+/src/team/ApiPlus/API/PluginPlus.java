package team.ApiPlus.API;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.Item;
import team.ApiPlus.Manager.ItemManager;

/**
 * PluginPlus class for use with API+.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
abstract public class PluginPlus extends JavaPlugin {
	
	abstract public void loadConfig(FileConfiguration con);
	
	/**
	 * Method used for building an Item and Adding it to the ItemManager.
	 * @param name String name of new Item.
	 * @param texture String texture of new Item.
	 * @param baseType String name of Type to base Item off.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public boolean addItem(String name, String texture, String baseType) {
		try {
			Item it = ItemManager.getInstance().buildItem(this, name, texture, baseType);
			ItemManager.getInstance().addItem(it);
			return true;
		} catch (Exception e) {
			Bukkit.getLogger().log(Level.INFO, String.format("[%s][ERROR] %s %s", this.getName(), e.getMessage(), e.getCause()));
			return false;
		}
	}
}
