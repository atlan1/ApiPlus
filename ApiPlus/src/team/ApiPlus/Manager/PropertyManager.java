package team.ApiPlus.Manager;

import java.util.Arrays;
import java.util.List;

import team.ApiPlus.API.PropertyHolder;

public class PropertyManager {

	
	public static void copyProperties(PropertyHolder input, PropertyHolder result){
		result.setProperties(input.getProperties());
	}
	
	public static void copySpecifiedKeys(PropertyHolder input, PropertyHolder result, String[] keys){
		copySpecifiedKeys(input, result, Arrays.asList(keys));
	}
	
	public static void copySpecifiedKeys(PropertyHolder input, PropertyHolder result, List<String> keys){
		for(String s : keys){
			if(input.getProperty(s)!=null){
				result.editProperty(s, input.getProperty(s));
				result.addProperty(s, input.getProperty(s));
			}
		}
	}
	
}
