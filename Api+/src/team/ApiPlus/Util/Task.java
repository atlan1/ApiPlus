package team.ApiPlus.Util;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * API+ Task Class.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class Task implements Runnable {
    private JavaPlugin plugin;
    private Object[] arguments;
    private int taskID = 0;
    
    /**
     * Method used for creating a Task.
     * @param plugin JavaPlugin to associate with.
     * @param arguments Arguments to use with Task.
     * @return Task Returns Instance of Task.
     */
    public static Task create(JavaPlugin plugin, Object... arguments) {
        return new Task(plugin, arguments);
    }
    
    private Task(JavaPlugin plugin, Object... arguments) {
        this.plugin = plugin;
        this.arguments = arguments;
    }
    
    /**
     * Method used for getting what plugin is associated with this Task.
     * @return JavaPlugin JavaPlugin associated with Task.
     */
    public JavaPlugin getPlugin() {
        return this.plugin;
    }
    
    /**
     * Method used for getting what server is associated with this Task.
     * @return Server Server associated with Task.
     */
    public Server getServer() {
        return this.plugin.getServer();
    }
    
    /**
     * Method used for getting an argument associated with this Task by Index.
     * @param index Integer of the Index.
     * @return Object The Argument found.
     */
    public synchronized Object getArg(int index) {
        return arguments[index];
    }
    
    /**
     * Method used for getting an argument as Integer associated with this Task by Index.
     * @param index Integer of Index.
     * @return Integer The Argument found.
     */
    public synchronized int getIntArg(int index) {
        return (Integer) getArg(index);
    }
    
    /**
     * Method used for getting an argument as Long associated with this Task by Index.
     * @param index Integer of Index.
     * @return Long The Argument found.
     */
    public synchronized long getLongArg(int index) {
        return (Long) getArg(index);
    }
    
    /**
     * Method used for getting an argument as Float associated with this Task by Index.
     * @param index Integer of Index.
     * @return Float The Argument found.
     */
    public synchronized float getFloatArg(int index) {
        return (Float) getArg(index);
    }
    
    /**
     * Method used for getting an argument as Double associated with this Task by Index.
     * @param index Integer of Index.
     * @return Double The Argument found.
     */
    public synchronized double getDoubleArg(int index) {
        return (Double) getArg(index);
    }
    
    /**
     * Method used for getting an argument as String associated with this Task by Index.
     * @param index Integer of Index.
     * @return String The Argument found.
     */
    public synchronized String getStringArg(int index) {
        return (String) getArg(index);
    }
    
    /**
     * Task Method that must be Overridden on use.
     */
    public void run() {}
    
    /**
     * Method used for checking if this Task is queued.
     * @return boolean True if is queued, false if not.
     */
    public boolean isTaskQueued() {
        return this.getServer().getScheduler().isQueued(this.taskID);
    }
    
    /**
     * Method used for checking if this Task is running.
     * @return boolean True if is running, false if not.
     */
    public boolean isTaskRunning() {
        return this.getServer().getScheduler().isCurrentlyRunning(this.taskID);
    }
    
    /**
     * Method used for stopping this Task.
     */
    public void stopTask() {
        this.getServer().getScheduler().cancelTask(this.taskID);
    }
    
    /**
     * Method used for starting this Task Asynchronously.
     */
    public void startTask() {
        startTask(false);
    }
    
    /**
     * Method used for starting this Task either Asynchronously or Synchronously.
     * @param Async boolean True if Asynchronously, False if Synchronously.
     */
    public void startTask(boolean Async) {
        startTaskDelayed(0, Async);
    }
    
    /**
     * Method used for starting this Task Asynchronously with supplied delay.
     * @param tickDelay long Delay to use, in Ticks.
     */
    public void startTaskDelayed(long tickDelay) {
        startTaskDelayed(tickDelay, false);
    }
    
    /**
     * Method used for starting this Task either Asynchronously or Synchronously with supplied delay.
     * @param tickDelay long Delay to use, in Ticks.
     * @param Async boolean True if Asynchronously, False if Synchronously.
     */
    public void startTaskDelayed(long tickDelay, boolean Async) {
        if (Async) {
            this.taskID = this.getServer().getScheduler().scheduleAsyncDelayedTask(this.plugin, this, tickDelay);
        } else {
            this.taskID = this.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, this, tickDelay);
        }
    }
    
    /**
     * Method used for starting this Task Asynchronously with repeating with supplied interval.
     * @param tickInterval long Interval to run Task, in Ticks.
     */
    public void startTaskRepeating(long tickInterval) {
        startTaskRepeating(tickInterval, false);
    }
    
    /**
     * Method used for starting this Task either Asynchronously or Synchronously with supplied interval.
     * @param tickInterval long Interval to run Task, in Ticks.
     * @param Async boolean True if Asynchronously, False if Synchronously.
     */
    public void startTaskRepeating(long tickInterval, boolean Async) {
        startTaskRepeating(0, tickInterval, Async);
    }
    
    /**
     * Method used for starting this Task either Asynchronously or Synchronously with supplied interval and start delay.
     * @param tickDelay long Delay before Task starts, in Ticks.
     * @param tickInterval long Interval to run Task, in Ticks.
     * @param Async boolean True if Asynchronously, False if Synchronously.
     */
    public void startTaskRepeating(long tickDelay, long tickInterval, boolean Async) {
        if (Async) {
            this.taskID = this.getServer().getScheduler().scheduleAsyncRepeatingTask(this.plugin, this, tickDelay, tickInterval);
        } else {
            this.taskID = this.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, this, tickDelay, tickInterval);
        }
    }
}
