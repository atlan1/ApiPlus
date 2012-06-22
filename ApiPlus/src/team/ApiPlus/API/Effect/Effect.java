package team.ApiPlus.API.Effect;

public interface Effect {

	EffectTarget getEffectTarget();
	void setEffectTarget(EffectTarget et);
	void performEffect(Object...arguments);
}
