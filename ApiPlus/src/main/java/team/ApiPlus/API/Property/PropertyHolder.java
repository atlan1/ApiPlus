package team.ApiPlus.API.Property;

import java.util.Map;

/** PropertyHolder that contains and manages properties of a class
 * @author Atlan1
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface PropertyHolder {
	public Property getProperty(String id);
	public void setProperty(String id, Property property);
	public void addProperty(String id, Property property);
	public void removeProperty(String id);
	public void editProperty(String id, Property property);
	public Map<String, Property> getProperties();
	public void setProperties(Map<String, Property> properties);
}
