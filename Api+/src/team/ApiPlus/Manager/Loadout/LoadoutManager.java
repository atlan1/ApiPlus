package team.ApiPlus.Manager.Loadout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import team.ApiPlus.ApiPlus;
import team.ApiPlus.API.PluginPlus;
import team.ApiPlus.Util.Utils;

/**
 * API+ Custom Loadout Manager.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class LoadoutManager {
	
	private static LoadoutManager instance;
	private List<Loadout> list = new ArrayList<Loadout>();
	
	private LoadoutManager() {
		if(instance != null) Utils.info("Cannot have multiple Instances of the Loadout Manager.");
	}
	
	/**
	 * Method used for adding a Loadout to Manager.
	 * @param l Loadout to be added.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public boolean addLoadout(Loadout l) {
		if(list.contains(l)) return false;
		else {
			list.add(l);
			return true;
		}
	}
	
	/**
	 * Method used for removing a Loadout from Manager.
	 * @param l Loadout to be removed.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public boolean removeLoadout(Loadout l) {
		if(list.contains(l)) {
			list.remove(l);
			return true;
		} else return false;
	}
	
	/**
	 * Method used for removing a Loadout from Manager by Name.
	 * @param name Name of Loadout to remove.
	 * @return boolean True if action completed succesfully, False if not.
	 */
	public boolean removeLoadout(String name) {
		return removeLoadout(getLoadout(name));
	}
	
	/**
	 * Method used for retrieving Loadout from Manager by Name.
	 * @param name Name of Loadout to retrieve.
	 * @return Loadout Loadout retrieved, null if none found.
	 */
	public Loadout getLoadout(String name) {
		for(Loadout l : list) {
			if(l.getName().equalsIgnoreCase(name)) return l;
		}
		return null;
	}
	
	/**
	 * Method used for Reading all loaded Loadouts.
	 * @return String Returns String message of any errors that have occurred.
	 */
	public String read() {
		StringBuilder b = new StringBuilder("Could not Load:");
		for(Loadout l : list) {
			if(ApiPlus.hooks.containsKey(l.getPlugin())) {
				Plugin p = Bukkit.getServer().getPluginManager().getPlugin(l.getPlugin());
				if(p instanceof PluginPlus) {
					Integer pVer = Integer.parseInt(p.getDescription().getVersion().replace(".", ""));
					Integer lpVer = Integer.parseInt(l.getVersion().replace(".", ""));
					if(pVer < lpVer) {
						b.append(l.getName() + "-Plugin version too low,");
					} else {
						for(FileConfiguration con : l.getConfigs()) {
							((PluginPlus) p).loadConfig(con);
						}
					}
				} else b.append(l.getName() + "-Plugin not on Server,");
			} else b.append(l.getName() + "-Plugin not API+ Enabled,");
		}
		if(b.toString().equalsIgnoreCase("Could not Load:")) {
			b.setLength(0);
			b.append("All Loadouts Loaded with no Errors.");
		}
		return b.toString();
	}
	
	/**
	 * Method used to load All Loadouts into Manager.
	 */
	public void loadAll() {
		list.clear();
		File main = new File(ApiPlus.getInstance().getDataFolder() + File.separator + "Loadouts");
		if(!main.exists()) main.mkdir();
		else {
			File[] l = main.listFiles();
			for(File f : l) {
				if(f.toString().endsWith(".zip")) {
					list.add(Loadout.create(f));
				}
			}
		}
	}
	
	/**
	 * Method used for getting an Instance of the API's LoadoutManager, Only one instance is allowed.
	 * @return LoadoutManager Instance of the API's LoadoutManager.
	 */
	public static LoadoutManager getInstance() {
		if(instance == null) instance = new LoadoutManager();
		return instance;
	}
}
