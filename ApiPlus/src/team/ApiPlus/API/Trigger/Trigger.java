package team.ApiPlus.API.Trigger;

public interface Trigger {

	public TriggerType getTriggerType();
	public void setTriggerType(TriggerType tt);
	public void activate(Object... args);
	public void triggered(Object... args);
}
