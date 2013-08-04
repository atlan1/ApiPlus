package test.team.ApiPlus.API.Property;

public abstract class Property<T> {

	private T value;
	
	public final T getValue(){
		return value;
	}
	
	public final void setValue(T t){
		value = t;
	}
	
}
