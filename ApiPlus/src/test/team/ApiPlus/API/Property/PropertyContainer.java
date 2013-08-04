package test.team.ApiPlus.API.Property;

import java.util.HashMap;
import java.util.Map;

/** This class is used for storing data easier, just create an object and you can use all easy to understand methods
 * @author Atlan1
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class PropertyContainer implements PropertyHolder{
	private Map<String, Property> properties = new HashMap<String, Property>();

	/**Constuctor initializes the properties field.
	 * @param props a Map<String, Object> containing the properties
	 */
	public PropertyContainer(Map<String, Property> props) {
		setProperties(props);
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
