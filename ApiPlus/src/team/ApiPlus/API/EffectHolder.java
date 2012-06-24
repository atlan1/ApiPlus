package team.ApiPlus.API;

import java.util.List;

import team.ApiPlus.API.Effect.Effect;

/**
 * @author Atlan1
 * @version 1.0
 */
public interface EffectHolder {

	public List<Effect> getEffects();
	public void setEffects(List<Effect> effects);
	public  void performEffects(Object... args);
}
