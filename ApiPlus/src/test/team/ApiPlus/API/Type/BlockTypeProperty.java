package test.team.ApiPlus.API.Type;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.Plugin;

import test.team.ApiPlus.API.Property.Property;
import test.team.ApiPlus.API.Property.PropertyHolder;
/**
 * @author Atlan1
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public abstract class BlockTypeProperty extends BlockType implements PropertyHolder {
	
	private Map<String, Property> properties = new HashMap<String, Property>();
	
	public BlockTypeProperty(Plugin plugin, String name, boolean isOpaque) {
		super(plugin, name, isOpaque);
	}
	
	@Override
	public Property getProperty(String id) {
		return properties.get(id);
	}

	@Override
	public void addProperty(String id, Property property) {
		if(!properties.containsKey(id))
			properties.put(id, property);
	}

	@Override
	public Map<String, Property> getProperties() {
		return properties;
	}

	@Override
	public void setProperties(Map<String, Property> properties) {
		this.properties = new HashMap<String, Property>(properties);
	}

	@Override
	public void removeProperty(String id) {
		if(properties.containsKey(id))
			properties.remove(id);
	}

	@Override
	public void editProperty(String id, Property property) {
		if(properties.containsKey(id))
			properties.put(id, property);
	}
	
	@Override
	public void setProperty(String id, Property property) {
		addProperty(id, property);
		editProperty(id, property);
	}

}
