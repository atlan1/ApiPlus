package team.ApiPlus;

import java.util.HashMap;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import team.ApiPlus.Manager.ItemManager;

/**
 * API+ Custom Item
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class Item extends GenericCustomItem {
	
	private HashMap<String, Float> values = new HashMap<String, Float>();
	private HashMap<String, String> resources = new HashMap<String, String>();
	private HashMap<String, Object> objects = new HashMap<String, Object>();
	
	public Item(GenericCustomItem it) {
		super(it.getPlugin(), it.getName(), it.getTexture());
		ItemManager.getInstance().addItem(this);
	}
	
	public Item(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
		ItemManager.getInstance().addItem(this);
	}
	
	/**
	 * Method used for getting corresponding Item Resource based on Input String.
	 * @param s The Name to look for.
	 * @return String
	 */
	public String getResource(String s) {
		return resources.containsKey(s) ? resources.get(s) : null;
	}

	/**
	 * Method used for setting Item Resource.
	 * @param name Name to be associated with the Resource.
	 * @param resource String to be used as Item Resource.
	 */
	public void setResource(String name, String resource) {
		this.resources.put(name, resource);
	}
	
	/**
	 * Method used for getting corresponding Item Object based on Input String.
	 * @param s The Name to search for.
	 * @return Object
	 */
	public Object getObject(String s) {
		return objects.containsKey(s) ? objects.get(s) : null;
	}

	/**
	 * Method used for setting Item Object.
	 * @param name Name to be associated with the Object.
	 * @param o Object to be used as Item Object.
	 */
	public void setObject(String name, Object o) {
		this.objects.put(name, o);
	}

	/**
	 * Method used for setting Item Value.
	 * @param name Name to be associated with the Value.
	 * @param value Value to be used as Item Value.
	 */
	public void setValue(String name, Float value) {
		values.put(name, value);
	}

	/**
	 * Method used for getting Item Value.
	 * @param name The Name to search for.
	 * @return
	 */
	public float getValue(String name) {
		return values.containsKey(name) ? values.get(name) : 0;
	}
	
	/**
	 * Method used for getting the HashMap of Resources.
	 * @return HashMap<String,String>
	 */
	public HashMap<String, String> getResources() {
		return resources;
	}
	
	/**
	 * Method used for getting the HashMap of Objects.
	 * @return HashMap<String,Object>
	 */
	public HashMap<String, Object> getObjects() {
		return objects;
	}
	
	/**
	 * Method used for getting the HashMap of Values.
	 * @return HashMap<String,Float>
	 */
	public HashMap<String, Float> getValues() {
		return values;
	}
	
	/**
	 * Method used for setting the HashMap of Resources. !!Warning, Overwrites any old data!!
	 * @param resources HashMap<String, String> containing replacement Resources.
	 * @see #setResource(String, String)
	 */
	public void setResources(HashMap<String, String> resources) {
		HashMap<String, String> list = new HashMap<String, String>(resources);
		this.resources = list;
	}

	/**
	 * Method used for setting the HashMap of Objects. !!Warning, Overwrites any old data!!
	 * @param objects HashMap<String, Objects> containing replacement Objects.
	 * @see #setObject(String, Object)
	 */
	public void setObjects(HashMap<String, Object> objects) {
		HashMap<String, Object> list = new HashMap<String, Object>(objects);
		this.objects = list;
	}

	/**
	 * Method used for setting the HashMap of Values. !!Warning, Overwrites any old data!!
	 * @param values HashMap<String, Float> containing replacement Values.
	 * @see #setValue(String, Float)
	 */
	public void setValues(HashMap<String, Float> values) {
		HashMap<String, Float> list = new HashMap<String, Float>(values);
		this.values = list;
	}
	
	/**
	 * Method for copying property data from one Item to this Item.
	 * @param parent Item to copy data from.
	 */
	public void copyData(Item parent) {
		this.setValues(parent.getValues());
		this.setResources(parent.getResources());
		this.setObjects(parent.getObjects());
	}
	
	/**
	 * Method used for checking if Item is empty, meaning it contains no values.
	 * @return boolean True if empty, False is contains data.
	 */
	public boolean isEmpty() {
		if(values.isEmpty() && objects.isEmpty() && resources.isEmpty()) return true;
		else return false;
	}
}
