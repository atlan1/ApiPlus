package team.ApiPlus;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.Manager.ConfigManager;
import team.ApiPlus.Manager.ItemManager;
import team.ApiPlus.Manager.TypeManager;
import team.ApiPlus.Manager.Loadout.LoadoutManager;
import team.ApiPlus.Util.FileUtil;
import team.ApiPlus.Util.Utils;

@SuppressWarnings("unused")
public class ApiPlus extends JavaPlugin {
	
	private String version;
	private static ApiPlus instance;
	private ItemManager iManager;
	private LoadoutManager lManager;
	private TypeManager tManager;
	private ConfigManager cManager;
	public static Map<String,Plugin> hooks = new HashMap<String,Plugin>();
	
	@Override
	public void onEnable() {
		instance = this;
		version = getDescription().getVersion();
		iManager = ItemManager.getInstance();
		lManager = LoadoutManager.getInstance();
		tManager = TypeManager.getInstance();
		cManager = ConfigManager.getInstance();
		lManager.loadAll();
		Utils.debug(lManager.read());
		hook();
		loadGeneral();
		Utils.info(String.format("API+ Version:%s Enabled.", version));
	}
	
	private void hook() {
		Plugin _wg = getServer().getPluginManager().getPlugin("WorldGuard");
		Plugin _fur = getServer().getPluginManager().getPlugin("FurnaceAPI");
		if(_wg != null) {
			hooks.put("WorldGuard", _wg);
			Utils.info("Hooked into WorldGuard");
		}
		if(_fur != null) {
			hooks.put("FurncaeAPI", _fur);
			Utils.info("Hooked into FurnaceAPI");
		}
	}
	
	private void loadGeneral() {
		File general = new File(this.getDataFolder().getPath() + File.separator + "general.yml");
		if(!general.exists()) FileUtil.copy(this.getResource("genearl.yml"), general);
		cManager.add(general);
		FileConfiguration con = cManager.get(general);
		if(con != null) {
			Utils.setDebug(Boolean.valueOf(con.getString("debug","false")));
		} else return;
	}
	
	/**
	 * Method used for adding a hook into API+.
	 * @param p Plugin to be added.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public static boolean addHook(Plugin p) {
		if(hooks.containsValue(p)) return false;
		else {
			hooks.put(p.getName(), p);
			return true;
		}
	}
	
	/**
	 * Method used for getting Server's Instance of Api+.
	 * @return ApiPlus Instance of Api+.
	 */
	public static ApiPlus getInstance() {
		return instance;
	}
}
