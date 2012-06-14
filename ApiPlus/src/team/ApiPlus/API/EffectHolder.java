package team.ApiPlus.API;

import java.util.List;

import team.ApiPlus.API.Effect.Effect;



public interface EffectHolder {

	public List<Effect> getEffects();
	public void setEffects(List<Effect> effects);
	public  <T> void performEffects(T... t);
}
