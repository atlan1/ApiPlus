package team.ApiPlus.API.Property;

import team.ApiPlus.Util.Utils;

public class NumberProperty extends Property<Number> implements PropertyModifier<Number>{

	private String svalue = null;
	
	public NumberProperty(String s) {
		if(s.matches("^[0-9.+-]+$")){
			if(s.startsWith("-"))
				svalue = "-";
			else if(s.startsWith("+"))
				svalue = "+";
			else
				svalue = null;
			this.setValue((Number)Double.parseDouble(removeSigns(s)));
		}else{
			Utils.warning("Check your configs for wrong value formatting: "+s);
		}
	}
	
	public NumberProperty(Number n){
		if(n.doubleValue()<0)
			svalue = "-";
		else if(n.doubleValue()>=0)
			svalue = "+";
		this.setValue(n);
	}
	
	@Override
	public Number modify(Number m) {
		switch(getModifyType(svalue)){
			case 1:
				return (Number)(((Number)m).doubleValue()+((Number)this.getValue()).doubleValue());
			case 2:
				return (Number)(((Number)m).doubleValue()+(-((Number)this.getValue()).doubleValue()));
			case 3:
				return m;
			default: return this.getValue();
		}
	}
	
	private static String removeSigns(String s){
		String news = s.replaceAll("[+-]", "");
		return news;
	}
	
	private static int getModifyType(String s){
		try{
			if(s.equals("+")){
				return 1;
			}else if(s.equals("-")){
				return 2;
			}
		}catch(NullPointerException e){
			return 3;
		}
		return 0;
	}
	
	public String toString(){
		return svalue;
	}
}
