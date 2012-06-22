package team.ApiPlus.Manager;

import java.util.Arrays;
import java.util.List;

import team.ApiPlus.API.PropertyHolder;

public class PropertyManager {

	
	/**Method used to copy all properties from one PropertyHolder to another
	 * @param input The PropertyHolder to copy from
	 * @param result The PropertyHolder to copy to
	 */
	public static void copyProperties(PropertyHolder input, PropertyHolder result){
		result.setProperties(input.getProperties());
	}
	
	/** Method used to copy the specified keys from one PH to another
	 * @param input PH to copy from
	 * @param result PH to copy to
	 * @param keys The keys to copy
	 */
	public static void copySpecifiedKeys(PropertyHolder input, PropertyHolder result, String[] keys){
		copySpecifiedKeys(input, result, Arrays.asList(keys));
	}
	
	/** Method used to copy the specified keys from one PH to another
	 * @param input PH to copy from
	 * @param result PH to copy to
	 * @param keys The keys to copy
	 */
	public static void copySpecifiedKeys(PropertyHolder input, PropertyHolder result, List<String> keys){
		for(String s : keys){
			if(input.getProperty(s)!=null){
				result.editProperty(s, input.getProperty(s));
				result.addProperty(s, input.getProperty(s));
			}
		}
	}
	
}
