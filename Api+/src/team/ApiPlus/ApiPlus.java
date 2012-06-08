package team.ApiPlus;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.Manager.ItemManager;
import team.ApiPlus.Manager.Loadout.LoadoutManager;
import team.ApiPlus.Util.Utils;

public class ApiPlus extends JavaPlugin {
	
	private static String version;
	private static ApiPlus instance;
	public static ItemManager iManager;
	public static LoadoutManager lManager;
	public static Map<String,Plugin> hooks = new HashMap<String,Plugin>();
	
	@Override
	public void onEnable() {
		instance = this;
		version = getDescription().getVersion();
		iManager = ItemManager.getInstance();
		lManager = LoadoutManager.getInstance();
		lManager.read();
		hook();
		
		Utils.info(String.format("API+ Version:%s Enabled.", version));
	}
	
	private void hook() {
		Plugin _wg = getServer().getPluginManager().getPlugin("WorldGuard");
		if(_wg != null) {
			hooks.put("WorldGuard", _wg);
			Utils.info("Hooked into WorldGuard");
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	/**
	 * Method used for getting Server's Instance of Api+.
	 * @return ApiPlus Instance of Api+.
	 */
	public static ApiPlus getInstance() {
		return instance;
	}
}
