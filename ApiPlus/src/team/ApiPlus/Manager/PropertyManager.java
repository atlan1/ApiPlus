package team.ApiPlus.Manager;

import team.ApiPlus.API.PropertyHolder;
import team.ApiPlus.API.Effect.EffectHolder;

public class PropertyManager {

	
	public static void copyProperties(PropertyHolder input, PropertyHolder result){
		result.setProperties(input.getProperties());
	}
	
	public static void copyEffects(EffectHolder input, EffectHolder result){
		result.setEffects(input.getEffects());
	}
	
}
