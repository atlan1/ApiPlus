package team.ApiPlus.Util;

import java.util.logging.Level;

import org.bukkit.Bukkit;

/**
 * Utility Class containing cross-class methods.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class Utils {
	private static boolean useDebug = false;
	
	/**
	 * Method used for writing a Info message to the Logger.
	 * @param message String message to write.
	 */
	public static void info(String message) {
		Bukkit.getLogger().log(Level.INFO, String.format("[API+] %s", message));
	}
	
	/**
	 * Method used for writing multiple Info messages to the Logger.
	 * @param message String Array of messages to write.
	 */
	public static void info(String[] message) {
		for(String s : message) {
			Bukkit.getLogger().log(Level.INFO, String.format("[API+] %s", s));
		}
	}
	
	/**
	 * Method used for writing a Warning message to the Logger.
	 * @param message String message to write.
	 */
	public static void warning(String message) {
		Bukkit.getLogger().log(Level.WARNING, String.format("[API+] %s", message));
	}
	
	/**
	 * Method used for writing multiple Warning messages to the Logger.
	 * @param message String Array of messages to write.
	 */
	public static void warning(String[] message) {
		for(String s : message) {
			Bukkit.getLogger().log(Level.WARNING, String.format("[API+] %s", s));
		}
	}
	
	/**
	 * Method used for setting Debug mode of API.
	 * @param b Boolean value to be set.
	 */
	public static void setDebug(boolean b) {
		useDebug = b;
	}
	
	/**
	 * Method used for a writing Debug message to the Logger.
	 * @param message String message to write.
	 */
	public static void debug(String message) {
		if(useDebug == false) return;
		Bukkit.getLogger().log(Level.INFO, String.format("[API+]Debug: %s", message));
	}
	
	/**
	 * Method used for writing multiple Debug messages to the Logger.
	 * @param message String Array of messages to write. 
	 */
	public static void debug(String[] message) {
		if(useDebug == false) return;
		for(String s : message) {
			Bukkit.getLogger().log(Level.INFO, String.format("[API+]Debug: %s", s));
		}
	}
}
