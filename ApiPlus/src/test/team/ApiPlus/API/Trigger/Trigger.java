package test.team.ApiPlus.API.Trigger;

import test.team.ApiPlus.Util.Task;

/**
 * @author Atlan1
 * @version 1.0
 */
public interface Trigger {

	public TriggerType getTriggerType();
	public void setTriggerType(TriggerType tt);
	public Task activate(Object... args);
	public Task triggered(Object... args);
}
