package team.ApiPlus.API.Effect;

/**
 * @author Atlan1
 * @version 1.0
 */
public interface Effect {

	EffectTarget getEffectTarget();
	void setEffectTarget(EffectTarget et);
	void performEffect(Object...arguments);
}
