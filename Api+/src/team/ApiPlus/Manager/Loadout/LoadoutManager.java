package team.ApiPlus.Manager.Loadout;

import java.util.ArrayList;
import java.util.List;

import team.ApiPlus.Util.Utils;

/**
 * API+ Custom Loadout Manager.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class LoadoutManager {
	
	private static LoadoutManager instance;
	private List<Loadout> list = new ArrayList<Loadout>();
	
	protected LoadoutManager() {
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
	 * Method used for getting an Instance of the API's LoadoutManager, Only one instance is allowed.
	 * @return LoadoutManager Instance of the API's LoadoutManager.
	 */
	public static LoadoutManager getInstance() {
		if(instance == null) instance = new LoadoutManager();
		return instance;
	}
}
