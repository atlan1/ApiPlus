package team.ApiPlus.Manager;

import org.getspout.spoutapi.material.item.GenericCustomItem;

import team.ApiPlus.ApiPlus;

public class TypeManager {

	public static Class<? extends GenericCustomItem> getType(String name) throws Exception{
		if(ApiPlus.getTypes().containsKey(name)){
			return ApiPlus.getTypes().get(name);
		}
		throw new Exception("Type not registered yet.");
	}
	
	public static  boolean isTypeRegistered(String name){
		if(ApiPlus.getTypes().containsKey(name))
			return true;
		return false;
	}
	
	public static void registerType(String name, Class<? extends GenericCustomItem> type) throws Exception{
		if(!ApiPlus.getTypes().containsKey(name))
			ApiPlus.getTypes().put(name, type);
		else throw new Exception("Already registered a type with this name.");
	}
	
	public static void unregisterType(String name) throws Exception{
		if(ApiPlus.getTypes().containsKey(name))
			ApiPlus.getTypes().remove(name);
		else throw new Exception("Already unregistered a type with this name.");
	}
}
