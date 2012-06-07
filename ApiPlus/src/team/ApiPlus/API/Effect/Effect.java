package team.ApiPlus.API.Effect;

import team.ApiPlus.API.PropertyHolder;


public interface Effect extends PropertyHolder{

	public EffectSection getEffectSection();
	public void setEffectSection(EffectSection e);
	public EffectType getEffectType();
	public void setEffectType(EffectType e);
}
