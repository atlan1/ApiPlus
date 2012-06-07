package team.ApiPlus.Manager;

import java.lang.reflect.Constructor;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.item.GenericCustomItem;

import team.ApiPlus.API.PluginPlus;

public class ItemManager {

	public static CustomItem buildItem(PluginPlus p, String name, String texture) throws Exception{
		Class<? extends GenericCustomItem> type = TypeManager.getType(name);
		CustomItem ci = null;
		Constructor<? extends GenericCustomItem> con = type.getConstructor(Plugin.class, String.class, String.class);
		ci = con.newInstance(p, name, texture);
		p.addItem(ci);
		return ci;
	}
	
}
