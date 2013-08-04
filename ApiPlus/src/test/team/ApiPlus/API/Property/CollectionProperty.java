package test.team.ApiPlus.API.Property;

import java.util.*;

public class CollectionProperty<T> extends Property<Collection<T>> implements PropertyModifier<Collection<T>>{

	public CollectionProperty(Collection<T> c) {
		this.setValue(c);
	}

	@Override
	public Collection<T> modify(Collection<T> t) {
		t.addAll(this.getValue());
		return t;
	}

	
	
}
