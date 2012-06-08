package team.ApiPlus;

import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.Manager.ItemManager;
import team.ApiPlus.Manager.Loadout.LoadoutManager;
import team.ApiPlus.Util.Utils;

public class ApiPlus extends JavaPlugin {
	
	private static String version;
	public static ItemManager iManager;
	public static LoadoutManager lManager;
	
	@Override
	public void onEnable() {
		version = getDescription().getVersion();
		iManager = ItemManager.getInstance();
		lManager = LoadoutManager.getInstance();
		
		Utils.info(String.format("API+ Version:%s Enabled.", version));
	}
	
	@Override
	public void onDisable() {
		
	}
}
