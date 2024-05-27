package com.mdval.utils;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

/**
 * @author federico
 *
 */
public class AppGlobalSingleton {
	
	private static AppGlobalSingleton instance;
	
	@Getter
	@Setter
	private HashMap<String, Object> properties;
	
	private AppGlobalSingleton() {
		properties = new HashMap<>();
	}
	
	public static AppGlobalSingleton getInstance() {
		if (instance == null) {
			instance = new AppGlobalSingleton();
		}
		return instance;
	}
	
	/**
	 * @param key
	 * @return
	 */
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
}
