package team.ApiPlus.API.Effect;

import java.util.List;



public interface EffectHolder {

	public List<Effect> getEffects();
	public void setEffects(List<Effect> effects);
	public void performEffects();
}
