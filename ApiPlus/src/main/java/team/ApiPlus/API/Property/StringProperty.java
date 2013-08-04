package team.ApiPlus.API.Property;

public class StringProperty extends Property<String> implements PropertyModifier<String>{

	public StringProperty(String s){
		this.setValue(s);
	}
	
	@Override
	public String modify(String t) {
		return t;
	}

}
