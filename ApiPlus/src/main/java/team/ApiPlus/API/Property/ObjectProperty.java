package team.ApiPlus.API.Property;

public class ObjectProperty<T> extends Property<T> implements PropertyModifier<T> {

	
	public ObjectProperty(T o) {
		this.setValue(o);
	}
	
	@Override
	public T modify(T t) {
		return t;
	}

}
