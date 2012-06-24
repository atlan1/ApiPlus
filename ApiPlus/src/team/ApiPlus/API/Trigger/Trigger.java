package team.ApiPlus.API.Trigger;

/**
 * @author Atlan1
 * @version 1.0
 */
public interface Trigger {

	public TriggerType getTriggerType();
	public void setTriggerType(TriggerType tt);
	public void activate(Object... args);
	public void triggered(Object... args);
}
